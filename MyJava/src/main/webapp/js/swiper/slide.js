
//设定轮播图片大小
var newSlideSize = function slideSize(){
    $(".swiper-container,.swiper-wrapper,.swiper-slide").height(340);
};
newSlideSize();
$(window).resize(function(){
    newSlideSize();
});

//设定轮播参数
window.onload = function(){
    var mySwiper = new Swiper('.swiper-container',{
        pagination: '.pagination',      //轮播底部圆点
        loop:true,                     //循环轮播
        autoplay:3000,                 //自动播放间隔
        paginationClickable: true,      // 轮播底部圆点是否可点击
        speed:600,                      //轮播图切换时间
        onTouchEnd:function(){
            mySwiper.startAutoplay();        //滑动图片后设置自动轮播
        }
    });
};