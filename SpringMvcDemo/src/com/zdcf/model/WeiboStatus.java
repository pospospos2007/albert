package com.zdcf.model;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.zdcf.weibo.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zdcf.weibo.Source;
import com.zdcf.weibo.StatusWapper;
import com.zdcf.weibo.Visible;
import com.zdcf.weibo.WeiboException;
import com.zdcf.weibo.WeiboResponseUtil;

public class WeiboStatus implements java.io.Serializable {
	private static final long serialVersionUID = -8795691786466526420L;
	private static Logger logger = LoggerFactory.getLogger(WeiboStatus.class.getName());
	//
	private WeiboUser weiboUser; // * 作者信息,not null,
	private Date createdAt; // * status创建时间 , "created_at":
	// "Tue May 31 17:46:55 +0800 2011",not null,
	private Long id; // * status id,not null,
	private String mid; // 微博MID,not null,
	private String idstr; // 保留字段，请勿使用
	private String text; // * 微博内容,not null
	private Source source; // * 微博来源
							// ,"<a href="http://weibo.com" rel="nofollow">新浪微博</a>",not
							// use it
	private boolean favorited; // 是否已收藏,not null
	private boolean truncated; // 是否被截断,not null
	private String inReplyToStatusId; // 回复ID,not use
	private String inReplyToUserId; // 回复人ID,not use
	private String inReplyToScreenName; // 回复人昵称,not use
	private String thumbnailPic; // 微博内容中的图片的缩略地址，没有时不返回
	private String bmiddlePic; // 中型图片，没有时不返回
	private String originalPic; // 原始图片，没有时不返回
	private WeiboStatus retweetedStatus; // 转发的博文，内容为status，如果不是转发，则没有此字段
	private String geo; // * 地理信息，可为null
	// "geo": {
	/*
	 * "type": "Point", "coordinates": [ 38.031418, 114.436623 ] }
	 */
	private double latitude = -1; // 纬度
	private double longitude = -1; // 经度
	private int repostsCount; // * 转发数
	private int commentsCount; // * 评论数
	private String annotations; // 元数据，没有时不返回此字段
	private int mlevel; // 暂未支持
	private Visible visible;

	public WeiboStatus() {
	}

	private void constructJson(JsonObject json) throws WeiboException {
		try {
			createdAt = WeiboResponseUtil.parseDate(json.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
			id = json.getJsonNumber("id").longValue();
			mid = json.getString("mid");
			idstr = json.getString("idstr");
			text = WeiboResponseUtil.withNonBmpStripped(json.getString("text"));
			if (!json.getString("source").isEmpty()) {
				source = new Source(WeiboResponseUtil.withNonBmpStripped(json.getString("source")));
			}
			inReplyToStatusId = json.getString("in_reply_to_status_id");
			inReplyToUserId = json.getString("in_reply_to_user_id");
			inReplyToScreenName = json.getString("in_reply_to_screen_name");
			favorited = json.getBoolean("favorited");
			truncated = json.getBoolean("truncated");
			thumbnailPic = JsonUtil.getString(json, "thumbnail_pic");
			bmiddlePic = JsonUtil.getString(json, "bmiddle_pic");
			originalPic = JsonUtil.getString(json, "original_pic");
			repostsCount = json.getInt("reposts_count");
			commentsCount = json.getInt("comments_count");
			if (json.containsKey("annotations"))
				annotations = json.getJsonArray("annotations").toString();
			if (!json.isNull("user"))
				weiboUser = new WeiboUser(json.getJsonObject("user"));
			if (json.containsKey("retweeted_status")) {
				retweetedStatus = new WeiboStatus(json.getJsonObject("retweeted_status"));
			}
			mlevel = json.getInt("mlevel");
			if (json.isNull("geo")) {
				geo = null;
			} else {
				geo = json.getJsonObject("geo").toString();
			}
			if (geo != null && !"".equals(geo) && !"null".equals(geo)) {
				getGeoInfo(geo);
			}
			if (!json.isNull("visible")) {
				visible = new Visible(json.getJsonObject("visible"));
			}
		} catch (JsonException je) {
			throw new WeiboException(je.getMessage() + ":" + json.toString(), je);
		}
	}

	private void getGeoInfo(String geo) {
		StringBuffer value = new StringBuffer();
		for (char c : geo.toCharArray()) {
			if (c > 45 && c < 58) {
				value.append(c);
			}
			if (c == 44) {
				if (value.length() > 0) {
					latitude = Double.parseDouble(value.toString());
					value.delete(0, value.length());
				}
			}
		}
		longitude = Double.parseDouble(value.toString());
	}

	public WeiboStatus(JsonObject json) throws JsonException, WeiboException {
		logger.trace("json : " + json);
		constructJson(json);
	}

	public WeiboStatus(String str) throws JsonException, WeiboException {
		// StatusStream uses this constructor
		logger.trace("json str : " + str);
		JsonReader jsonReader = Json.createReader(new StringReader(str));
		JsonObject json = jsonReader.readObject();
		jsonReader.close();
		constructJson(json);
	}

	public static StatusWapper constructWapperStatus(JsonObject res) throws WeiboException {
		// JsonObject jsonStatus = res.asJsonObject(); // asJsonArray();
		JsonObject jsonStatus = res;
		JsonArray statuses = null;
		try {
			if (!jsonStatus.isNull("statuses")) {
				statuses = jsonStatus.getJsonArray("statuses");
			}
			int size = statuses.size();
			List<WeiboStatus> status = new ArrayList<WeiboStatus>(size);
			for (int i = 0; i < size; i++) {
				status.add(new WeiboStatus(statuses.getJsonObject(i)));
			}
			long previousCursor = jsonStatus.getJsonNumber("previous_cursor").longValue();
			long nextCursor = jsonStatus.getJsonNumber("next_cursor").longValue();
			long totalNumber = jsonStatus.getJsonNumber("total_number").longValue();
			String hasvisible = String.valueOf(jsonStatus.getBoolean("hasvisible"));
			return new StatusWapper(status, previousCursor, nextCursor, totalNumber, hasvisible);
		} catch (JsonException jsone) {
			throw new WeiboException(jsone);
		}
	}

	public WeiboUser getUser() {
		return weiboUser;
	}

	public void setUser(WeiboUser weiboUser) {
		this.weiboUser = weiboUser;
	}

	public String getIdstr() {
		return idstr;
	}

	public void setIdstr(String idstr) {
		this.idstr = idstr;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public boolean isFavorited() {
		return favorited;
	}

	public void setFavorited(boolean favorited) {
		this.favorited = favorited;
	}

	public String getInReplyToStatusId() {
		return inReplyToStatusId;
	}

	public void setInReplyToStatusId(String inReplyToStatusId) {
		this.inReplyToStatusId = inReplyToStatusId;
	}

	public String getInReplyToUserId() {
		return inReplyToUserId;
	}

	public void setInReplyToUserId(String inReplyToUserId) {
		this.inReplyToUserId = inReplyToUserId;
	}

	public String getInReplyToScreenName() {
		return inReplyToScreenName;
	}

	public void setInReplyToScreenName(String inReplyToScreenName) {
		this.inReplyToScreenName = inReplyToScreenName;
	}

	public String getThumbnailPic() {
		return thumbnailPic;
	}

	public void setThumbnailPic(String thumbnailPic) {
		this.thumbnailPic = thumbnailPic;
	}

	public String getBmiddlePic() {
		return bmiddlePic;
	}

	public void setBmiddlePic(String bmiddlePic) {
		this.bmiddlePic = bmiddlePic;
	}

	public String getOriginalPic() {
		return originalPic;
	}

	public void setOriginalPic(String originalPic) {
		this.originalPic = originalPic;
	}

	public WeiboStatus getRetweetedStatus() {
		return retweetedStatus;
	}

	public void setRetweetedStatus(WeiboStatus retweetedStatus) {
		this.retweetedStatus = retweetedStatus;
	}

	public String getGeo() {
		return geo;
	}

	public void setGeo(String geo) {
		this.geo = geo;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getRepostsCount() {
		return repostsCount;
	}

	public void setRepostsCount(int repostsCount) {
		this.repostsCount = repostsCount;
	}

	public int getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getAnnotations() {
		return annotations;
	}

	public void setAnnotations(String annotations) {
		this.annotations = annotations;
	}

	public int getMlevel() {
		return mlevel;
	}

	public void setMlevel(int mlevel) {
		this.mlevel = mlevel;
	}

	public Visible getVisible() {
		return visible;
	}

	public void setVisible(Visible visible) {
		this.visible = visible;
	}

	public boolean isTruncated() {
		return truncated;
	}

	public void setTruncated(boolean truncated) {
		this.truncated = truncated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof WeiboStatus))
			return false;
		WeiboStatus cobj = (WeiboStatus) obj;
		if (!cobj.getId().equals(getId()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Status [user=" + weiboUser + ", idstr=" + idstr + ", createdAt=" + createdAt + ", id=" + id + ", text=" + text + ", source=" + source + ", favorited=" + favorited + ", truncated=" + truncated + ", inReplyToStatusId=" + inReplyToStatusId
				+ ", inReplyToUserId=" + inReplyToUserId + ", inReplyToScreenName=" + inReplyToScreenName + ", thumbnailPic=" + thumbnailPic + ", bmiddlePic=" + bmiddlePic + ", originalPic=" + originalPic + ", retweetedStatus=" + retweetedStatus
				+ ", geo=" + geo + ", latitude=" + latitude + ", longitude=" + longitude + ", repostsCount=" + repostsCount + ", commentsCount=" + commentsCount + ", mid=" + mid + ", annotations=" + annotations + ", mlevel=" + mlevel + ", visible="
				+ visible + "]";
	}
}
