package org.myself.mobile.web.bean;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-10-19
 * Time: 下午2:22
 * To change this template use File | Settings | File Templates.
 */
public class IsbnInfo {
//    title 	string
//    subtitle 	string 	副名
//    isbn10 	string 	10位isbn编号
//    isbn13 	string 	13位isbn编号
//    author_info 	string 	作者介绍
//    pages 	int 	页数
//    author 	string 	作者
//    translator 	string 	译者
//    price 	string 	定价
//    binding 	string 	装帧
//    publisher 	string 	出版社
//    pubdate 	string 	出版日期
    private  String title;

    private String  subTitle;

    private String  isbn10;

    private String  isbn13;

    private String  authorInfo;

    private String  pages;

    private String  translator;

    private String  price;

    private String  binding;

    private String  author;

    private String  publisher;

    private String  pubdate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getAuthorInfo() {
        return authorInfo;
    }

    public void setAuthorInfo(String authorInfo) {
        this.authorInfo = authorInfo;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }
}
