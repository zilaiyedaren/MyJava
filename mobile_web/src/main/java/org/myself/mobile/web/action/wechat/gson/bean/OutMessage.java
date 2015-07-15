package org.myself.mobile.web.action.wechat.gson.bean;

import java.util.ArrayList;
import java.util.List;

public class OutMessage {

	private String ToUserName;
	private String FromUserName;
	private Long CreateTime;
	private String MsgType = "text";
	private Long MsgId;
	// 文本消息
	private String Content;

	private int FuncFlag = 0;

	private String MusicUrl;
	private String HQMusicUrl;

	private Integer ArticleCount;
	private String Title;
	private String Description;
	private String PicUrl;
	private String Url;

	private List<org.myself.mobile.web.action.wechat.gson.bean.Articles> Articles;

    private org.myself.mobile.web.action.wechat.gson.bean.Music Music;

    public org.myself.mobile.web.action.wechat.gson.bean.Music getMusic() {
        return Music;
    }

    public void setMusic(org.myself.mobile.web.action.wechat.gson.bean.Music music) {
        Music = music;
    }

    public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public Long getMsgId() {
		return MsgId;
	}

	public void setMsgId(Long msgId) {
		MsgId = msgId;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public int getFuncFlag() {
		return FuncFlag;
	}

	public void setFuncFlag(int funcFlag) {
		FuncFlag = funcFlag;
	}

	public String getMusicUrl() {
		return MusicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		MusicUrl = musicUrl;
	}

	public String getHQMusicUrl() {
		return HQMusicUrl;
	}

	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}

	public int getArticleCount() {
		if (this.Articles != null && Articles.size() > 0)
			return this.Articles.size();
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public List<org.myself.mobile.web.action.wechat.gson.bean.Articles> getArticles() {
		return Articles;
	}

	public void setArticles(List<org.myself.mobile.web.action.wechat.gson.bean.Articles> articles) {
		if(articles.size()>10)
			articles  = new ArrayList<org.myself.mobile.web.action.wechat.gson.bean.Articles>(articles.subList(0, 10));
		Articles = articles;
	}
}