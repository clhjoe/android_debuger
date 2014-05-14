package adb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;

import object.ObjectElement;
import object.Singletone;

public abstract class ADBCommnad {
	protected Runtime rt = Runtime.getRuntime();
	protected Process proc;
	protected Singletone singletone;
	private static int sleepTime = 1;
	private int timeout = 1000;

	public ADBCommnad(Singletone singletone) {
		this.singletone = singletone;
	}

	public void Initial() {
		String list[] = null;
		int total;
		String[] res = readValue(0, 0, 7).split("\n");
		singletone.atmel.io.setInfoMsg(res);

		if (res != null && res.length >= 6) {
			total = Integer.parseInt(res[6]);
			res = readValue(7, 0, (Integer.parseInt(res[6]) * 6) + 3).split(
					"\n");
			String tag;
			for (int i = 0; i < total; i++) {
				tag = singletone.atmel.getNameMapping(Integer
						.parseInt(res[0 + i * 6]));
				if (tag == null || tag.equals(""))
					tag = "unknown";
				singletone.atmel.ObjectElemens.put(
						tag,
						new ObjectElement(tag,
								Integer.parseInt(res[2 + i * 6]), Integer
										.parseInt(res[1 + i * 6]), Integer
										.parseInt(res[3 + i * 6]),
								res[4 + i * 6], res[5 + i * 6]));
			}

		}

	}

	public String[] getPowerConfig() {
		ObjectElement oe = singletone.atmel.getObject(7);
		if (oe == null)
			return null;
		String res = readValue(oe.getLbyte(), oe.getHbyte(), oe.getSize() + 1);
		if (res != null)
			return res.split("\n");
		else
			return null;
	}

	public String[] getMultiTouch() {
		ObjectElement oe = singletone.atmel.getObject(9);
		if (oe == null)
			return null;
		String res = readValue(oe.getLbyte(), oe.getHbyte(), 0x22);
		if (res != null)
			return res.split("\n");
		else
			return null;
	}

	public String[] getPalm() {

		ObjectElement oe = singletone.atmel.getObject(41);
		if (oe == null)
			return null;
		String res = readValue(oe.getLbyte(), oe.getHbyte(), oe.getSize() + 1);
		if (res != null)
			return res.split("\n");
		else
			return null;
	}

	protected abstract String readValue(int lbyte, int hbyte, int i);

	private String verifyPath(String str) {
		return str.replace("\\", "\\\\");
	}

	protected String executeCMD(String[] list, boolean readVal) {
		String read = "";
		try {
			proc = rt.exec(list);
			Worker worker = new Worker(proc, readVal);
			worker.start();
			try {
				worker.join(timeout);
				if (worker.exit != null) {
					return worker.read;
				} else
					throw new TimeoutException();
			} catch (InterruptedException ex) {
				worker.interrupt();
				Thread.currentThread().interrupt();
				throw ex;
			} catch (TimeoutException e) {
				System.out.println("Timeout");
			} finally {
				proc.destroy();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return read;
	}

	static class Worker extends Thread {
		private final Process process;
		Integer exit;
		private boolean isReadVal = false;
		String read = "";

		Worker(Process process, boolean isReadVal) {
			this.process = process;
			this.isReadVal = isReadVal;
		}

		public void run() {
			try {
				if (isReadVal) {
					BufferedReader br = new BufferedReader(
							new InputStreamReader(process.getInputStream()));
					String buf = null;

					while ((buf = br.readLine()) != null) {
						read += buf + "\n";
					}
				}
				this.sleep(sleepTime);
				exit = process.waitFor();
				//System.out.println("d " + read);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
