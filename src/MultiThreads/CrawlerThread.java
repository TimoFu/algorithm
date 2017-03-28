package MultiThreads;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

class CrawlerThread extends Thread {
	private static AtomicLong counter;
	private static BlockingQueue<String> queue = new LinkedBlockingQueue<String>();

	private static HashMap<String, Boolean> mp = new HashMap<String, Boolean>();
	private static List<String> results = new ArrayList<String>();

	public static void setFirstUrl(String url, int thread_num) {
		counter = new AtomicLong(thread_num);

		try {
			queue.put(url);
		} catch (InterruptedException e) {
			// e.printStackTrace();
		}
	}

	public static Long getCounter() {
		return counter.get();
	}

	public static List<String> getResults() {
		return results;
	}

	@Override
	public void run() {
		while (true) {
			String url = "";
			try {
				counter.decrementAndGet();
				url = queue.take();
				counter.incrementAndGet();
			} catch (Exception e) {
				// e.printStackTrace();
				break;
			}

			String domain = "";
			try {
				URL netUrl = new URL(url);
				domain = netUrl.getHost();
			} catch (MalformedURLException e) {
				// e.printStackTrace();
			}
			if (!mp.containsKey(url) && domain.endsWith("wikipedia.org")) {
				mp.put(url, true);
				results.add(url);
				List<String> urls = new ArrayList<String>();// HtmlHelper.parseUrls(url);
				for (String u : urls) {
					try {
						queue.put(u);
					} catch (InterruptedException e) {
						// e.printStackTrace();
					}
				}
			}
		}
	}
}
