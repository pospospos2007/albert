package com.zdcf.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonException;
import javax.json.JsonObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zdcf.weibo.UserWapper;
import com.zdcf.weibo.WeiboException;
import com.zdcf.weibo.WeiboResponseUtil;

public class WeiboUser implements java.io.Serializable {
	private static final long serialVersionUID = -332738032648843482L;
	private static Logger logger = LoggerFactory.getLogger(User.class.getName());
	private long id; // * 用户UID,must have,not null,json long to long
	private String idstr; // 字符串型的用户UID,not used
	private String screenName; // * 微博昵称,must have,not null,json string to string
	private String name; // 友好显示名称，如Bill Gates,名称中间的空格正常显示(此特性暂不支持),must have,not null,the same to screenName,json string to string
	private int province; // * 省份编码（参考省份编码表),must have,not null,json string to int
	private int city; // * 城市编码（参考城市编码表）,must have,not null,json string to int
	private String location; // * 地址,must have,not null,json string
	private String description; // * 个人描述 ,must have,not null,json string
	private String url; // * 用户博客地址,must have
	private String profileImageUrl; // * 用户头像地址（中图），50×50像素,must have
	private String domain; // * 用户个性化URL,must have
	private String weihao; // 微號,must have
	private String gender; // * 性别,m--男，f--女,n--未知,must have
	private int followersCount; // * 粉丝数,must have
	private int friendsCount; // * 关注数,must have
	private int statusesCount; // * 微博数,must have
	private int favouritesCount; // 收藏数,must have
	private Date createdAt; // * 创建时间,must have
	private boolean following; // 保留字段,是否已关注(此特性暂不支持),must have
	private boolean verified; // * 加V标示，是否微博认证用户,must have
	private int verifiedType; // 认证类型,暂未支持,must have
	private boolean allowAllActMsg; // 是否允许所有人给我发私信,must have
	private boolean allowAllComment; // 是否允许所有人对我的微博进行评论,must have
	private boolean followMe; // 此用户是否关注我,must have
	private String avatarLarge; // 大头像地址,must have
	private int onlineStatus; // 用户在线状态,must have
	private WeiboStatus weiboStatus; // 用户最新一条微博
	private int biFollowersCount; // 互粉数,must have
	private String remark; // 备注信息，在查询用户关系时提供此字段。
	private String lang; // 用户语言版本
	private String verifiedReason; // * 认证原因

	public WeiboUser() {
	}

	private void init(JsonObject json) throws WeiboException {
		if (json != null) {
			try {
				id = json.getJsonNumber("id").longValue();
				screenName = json.getString("screen_name");
				name = json.getString("name");
				province = Integer.parseInt(json.getString("province"));
				city = Integer.parseInt(json.getString("city"));
				location = json.getString("location");
				description = WeiboResponseUtil.withNonBmpStripped(json.getString("description"));
				url = json.getString("url");
				profileImageUrl = json.getString("profile_image_url");
				domain = json.getString("domain");
				gender = json.getString("gender");
				followersCount = json.getInt("followers_count");
				friendsCount = json.getInt("friends_count");
				favouritesCount = json.getInt("favourites_count");
				statusesCount = json.getInt("statuses_count");
				createdAt = WeiboResponseUtil.parseDate(json.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
				following = json.getBoolean("following");
				verified = json.getBoolean("verified");
				verifiedType = json.getInt("verified_type");
				verifiedReason = json.getString("verified_reason");
				allowAllActMsg = json.getBoolean("allow_all_act_msg");
				allowAllComment = json.getBoolean("allow_all_comment");
				followMe = json.getBoolean("follow_me");
				avatarLarge = json.getString("avatar_large");
				onlineStatus = json.getInt("online_status");
				biFollowersCount = json.getInt("bi_followers_count");
				if (!json.getString("remark").isEmpty()) {
					remark = json.getString("remark");
				}
				lang = json.getString("lang");
				weihao = json.getString("weihao");
			} catch (JsonException jsone) {
				throw new WeiboException(jsone.getMessage() + ":" + json.toString(), jsone);
			}
		}
	}

	public static String[] constructIds(JsonObject res) throws WeiboException {
		try {
			JsonArray list = res.getJsonArray("ids");
			String temp = list.toString().substring(1, list.toString().length() - 1);
			String[] ids = temp.split(",");
			return ids;
		} catch (JsonException jsone) {
			throw new WeiboException(jsone.getMessage() + ":" + jsone.toString(), jsone);
		}
	}

	/**
	 * 
	 * @param res
	 * @return
	 * @throws WeiboException
	 */
	public static UserWapper constructWapperUsers(JsonObject res) throws WeiboException {
		logger.trace(res.toString());
		JsonObject jsonUsers = res; // asJsonArray();
		try {
			JsonArray user = jsonUsers.getJsonArray("users");
			int size = user.size();
			List<WeiboUser> users = new ArrayList<WeiboUser>(size);
			for (int i = 0; i < size; i++) {
				users.add(new WeiboUser(user.getJsonObject(i)));
			}
			long previousCursor = WeiboResponseUtil.getLong("previous_curosr", jsonUsers);
			long nextCursor = WeiboResponseUtil.getLong("next_cursor", jsonUsers);
			long totalNumber = WeiboResponseUtil.getLong("total_number", jsonUsers);
			String hasvisible = jsonUsers.getString("hasvisible");
			return new UserWapper(users, previousCursor, nextCursor, totalNumber, hasvisible);
		} catch (JsonException jsone) {
			throw new WeiboException(jsone);
		}
	}

	/**
	 * @param res
	 * @return
	 * @throws WeiboException
	 */
	static List<WeiboUser> constructResult(JsonArray res) throws WeiboException {
		logger.trace(res.toString());
		JsonArray list = res;
		try {
			int size = list.size();
			List<WeiboUser> users = new ArrayList<WeiboUser>(size);
			for (int i = 0; i < size; i++) {
				users.add(new WeiboUser(list.getJsonObject(i)));
			}
			return users;
		} catch (JsonException e) {
		}
		return null;
	}

	public String getVerified_reason() {
		return verifiedReason;
	}

	public void setVerified_reason(String verifiedReason) {
		this.verifiedReason = verifiedReason;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProvince(int province) {
		this.province = province;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public void setDomain(String userDomain) {
		this.domain = userDomain;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setFollowersCount(int followersCount) {
		this.followersCount = followersCount;
	}

	public void setFriendsCount(int friendsCount) {
		this.friendsCount = friendsCount;
	}

	public void setStatusesCount(int statusesCount) {
		this.statusesCount = statusesCount;
	}

	public void setFavouritesCount(int favouritesCount) {
		this.favouritesCount = favouritesCount;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setFollowing(boolean following) {
		this.following = following;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public void setVerifiedType(int verifiedType) {
		this.verifiedType = verifiedType;
	}

	public void setAllowAllActMsg(boolean allowAllActMsg) {
		this.allowAllActMsg = allowAllActMsg;
	}

	public void setAllowAllComment(boolean allowAllComment) {
		this.allowAllComment = allowAllComment;
	}

	public void setFollowMe(boolean followMe) {
		this.followMe = followMe;
	}

	public void setAvatarLarge(String avatarLarge) {
		this.avatarLarge = avatarLarge;
	}

	public void setOnlineStatus(int onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	public void setStatus(WeiboStatus weiboStatus) {
		this.weiboStatus = weiboStatus;
	}

	public void setBiFollowersCount(int biFollowersCount) {
		this.biFollowersCount = biFollowersCount;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getWeihao() {
		return weihao;
	}

	public void setWeihao(String weihao) {
		this.weihao = weihao;
	}

	public String getVerifiedReason() {
		return verifiedReason;
	}

	public void setVerifiedReason(String verifiedReason) {
		this.verifiedReason = verifiedReason;
	}

	public String getUrl() {
		return url;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public int getVerifiedType() {
		return verifiedType;
	}

	public boolean isAllowAllActMsg() {
		return allowAllActMsg;
	}

	public boolean isAllowAllComment() {
		return allowAllComment;
	}

	public boolean isFollowMe() {
		return followMe;
	}

	public String getAvatarLarge() {
		return avatarLarge;
	}

	public int getOnlineStatus() {
		return onlineStatus;
	}

	public int getBiFollowersCount() {
		return biFollowersCount;
	}

	public WeiboUser(JsonObject json) throws WeiboException {
		super();
		init(json);
	}

	public long getId() {
		return id;
	}

	public String getScreenName() {
		return screenName;
	}

	public String getName() {
		return name;
	}

	public int getProvince() {
		return province;
	}

	public int getCity() {
		return city;
	}

	public String getLocation() {
		return location;
	}

	public String getDescription() {
		return description;
	}

	public URL getProfileImageURL() {
		try {
			return new URL(profileImageUrl);
		} catch (MalformedURLException ex) {
			return null;
		}
	}

	public URL getURL() {
		try {
			return new URL(url);
		} catch (MalformedURLException ex) {
			return null;
		}
	}

	public String getDomain() {
		return domain;
	}

	public String getGender() {
		return gender;
	}

	public int getFollowersCount() {
		return followersCount;
	}

	public int getFriendsCount() {
		return friendsCount;
	}

	public int getStatusesCount() {
		return statusesCount;
	}

	public int getFavouritesCount() {
		return favouritesCount;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public boolean isFollowing() {
		return following;
	}

	public boolean isVerified() {
		return verified;
	}

	public int getverifiedType() {
		return verifiedType;
	}

	public boolean isallowAllActMsg() {
		return allowAllActMsg;
	}

	public boolean isallowAllComment() {
		return allowAllComment;
	}

	public boolean isfollowMe() {
		return followMe;
	}

	public String getavatarLarge() {
		return avatarLarge;
	}

	public int getonlineStatus() {
		return onlineStatus;
	}

	public WeiboStatus getStatus() {
		return weiboStatus;
	}

	public int getbiFollowersCount() {
		return biFollowersCount;
	}

	public String getRemark() {
		return remark;
	}

	public String getLang() {
		return lang;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeiboUser other = (WeiboUser) obj;
		if (!(id == (other.id)))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [" + "id=" + id + ", screenName=" + screenName + ", name=" + name + ", province=" + province + ", city=" + city + ", location=" + location + ", description=" + description + ", url=" + url + ", profileImageUrl="
				+ profileImageUrl + ", userDomain=" + domain + ", gender=" + gender + ", followersCount=" + followersCount + ", friendsCount=" + friendsCount + ", statusesCount=" + statusesCount + ", favouritesCount=" + favouritesCount
				+ ", createdAt=" + createdAt + ", following=" + following + ", verified=" + verified + ", verifiedType=" + verifiedType + ", allowAllActMsg=" + allowAllActMsg + ", allowAllComment=" + allowAllComment + ", followMe=" + followMe
				+ ", avatarLarge=" + avatarLarge + ", onlineStatus=" + onlineStatus + ", status=" + weiboStatus + ", biFollowersCount=" + biFollowersCount + ", remark=" + remark + ", lang=" + lang + ", verifiedReason=" + verifiedReason + ", weihao="
				+ weihao + "]";
	}
}
