package com.zdcf.weibo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zdcf.model.WeiboStatus;

public class GetPublicTlAll {
	private Timeline tm;
	private Task t;
	//
	private DownloadPic dt;// download pic
	private int count; // how many weibos are downloaded
	private static Logger log = LoggerFactory.getLogger(GetPublicTlAll.class.getName());

	public GetPublicTlAll(Timeline tm,Task t) {
		this.tm=tm;
		this.t=t;
		if (t.isDownloadPicture())
			dt = new DownloadPic(t.getName());
	}

	public List<WeiboStatus> returnall() throws IOException {
		List<WeiboStatus> returnall = new ArrayList<WeiboStatus>();
		StatusWapper statusa = null;
		try {
			statusa = tm.getPublicTimeline(200, 0);
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		log.info("获取新浪微博当前页总数  : " + statusa.getTotalNumber());
		if (!statusa.getStatuses().isEmpty()) {
			for (WeiboStatus s : statusa.getStatuses()) {
				String stext = s.getText();
				if (t.isDownloadPicture()) {
					if (s.getThumbnailPic() != null && s.getThumbnailPic().length() > 3) {
						String statusid = String.valueOf(s.getId());
						dt.setFilename(statusid);
						// begin saving pic
						try {
							dt.write(s.getThumbnailPic(), s.getBmiddlePic(), s.getOriginalPic());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}// end saving pic
				if (t.getKeywords() != null && !t.getKeywords().isEmpty()) {
					for (String keyw : t.getKeywords()) {
						// if (stext.indexOf(keyw) != -1) {
						if (Pattern.compile(keyw).matcher(stext).find()) {
							returnall.add(s);
							count++;
							break;
						} else {
							// System.out.println(stext + "不含关键词"
							// + keyw);
						}
					}
				} else {
					returnall.add(s);
					count++;
				}
			}
		} else {
			return null;
		}
		return returnall;
	}

	public Timeline getTm() {
		return tm;
	}

	public void setTm(Timeline tm) {
		this.tm = tm;
	}

	public int getCount() {
		return count;
	}

	public Task getT() {
		return t;
	}

	public void setT(Task t) {
		this.t = t;
	}
}
