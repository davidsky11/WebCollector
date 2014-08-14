package com.kn.zhihu;

import java.io.IOException;
import java.util.regex.Pattern;

import com.kn.crawler.BreadthCrawler;
import com.kn.model.Page;
import com.kn.util.ProxySelector;

public class ZhihuCrawler extends BreadthCrawler {

	/* visit函数定制访问每个页面时所需进行的操作 */
	@Override
	public void visit(Page page) {
		//String question_regex = "^http://www.zhihu.com/question/[0-9]+";
		String question_regex = "http://news.cnblogs.com/n/[0-9]+";
		if (Pattern.matches(question_regex, page.url)) {
			System.out.println("正在抽取" + page.url);
			/* 抽取标题 */
			String title = page.doc.title();
			System.out.println(title);
			/* 抽取提问内容 */
			String question = page.doc.select("div[id=zh-question-detail]")
					.text();
			System.out.println(question);

		}
	}

	/* 启动爬虫 */
	public static void main(String[] args) throws IOException {
		ProxySelector ps = new ProxySelector();
		ps.setLocalProxy();
		ZhihuCrawler crawler = new ZhihuCrawler();
		//crawler.addSeed("http://www.zhihu.com/question/21003086");
		crawler.addSeed("http://news.cnblogs.com/");
		crawler.start(2);
		
		ps.removeLocalProxy();
	}
}
