package MultiThreads;

import java.util.List;

public class WebCrawler {
	/**
	 * @param url
	 *            a url of root page
	 * @return all urls
	 */
	public List<String> crawler(String url) {
		// Write your code here
		int thread_num = 7;
		CrawlerThread.setFirstUrl(url, thread_num);

		CrawlerThread[] thread_pools = new CrawlerThread[thread_num];
		for (int i = 0; i < thread_num; ++i) {
			thread_pools[i] = new CrawlerThread();
			thread_pools[i].start();
		}

		while (CrawlerThread.getCounter() > 0)
			;

		for (int i = 0; i < thread_num; ++i) {
			thread_pools[i].stop();
		}

		List<String> results = CrawlerThread.getResults();
		return results;
	}
}