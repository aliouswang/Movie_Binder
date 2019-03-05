package com.example.aliouswang.movie_binder;

import com.example.aliouswang.movie_binder.bean.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieMock {

    private static String[] movieNames = {
            "流浪地球",
            "疯狂的外星人",
            "飞驰人生",
            "一出好戏",
            "心花路放",
            "战狼2"
    };

    private static String[] movieImages = {
            "https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike180%2C5%2C5%2C180%2C60/sign=c3299fd84c34970a537e187df4a3baad/dcc451da81cb39dbdd0730a1dd160924aa183005.jpg",
            "https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike180%2C5%2C5%2C180%2C60/sign=36b2ee94a7014c080d3620f76b12696d/0df3d7ca7bcb0a462d89e8046563f6246b60af60.jpg",
            "https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=959ac755a4773912d02b8d339970ed7d/0b7b02087bf40ad10140460b5a2c11dfa8ecceb6.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551620814820&di=139d753f16ffed35ec645944fbbda846&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn15%2F488%2Fw870h1218%2F20181011%2Ff120-hmhafiq9164280.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551620874042&di=130cf619a723f998e4187d6ef7bed384&imgtype=0&src=http%3A%2F%2Fmf19.yifutu.com%2Fmoban%2F1508%2F25%2F17%2F744292_97a688ea-e1c2-4036-a3da-78794c03e2df.Png",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551620941502&di=9e74334624e5f11fa42de6b2f0b850bd&imgtype=0&src=http%3A%2F%2Fimage14.m1905.cn%2Fuploadfile%2F2017%2F0717%2Fthumb_0_647_500_20170717091140118935_watermark.jpg"
    };

    public static List<Movie> mockMovies() {
        List<Movie> resultList = new ArrayList<>();
        int defaultHot = 0;
        for (int i = 0; i < movieNames.length; i++) {
            resultList.add(new Movie(i + 1, movieNames[i], movieImages[i], defaultHot));
        }
        return resultList;
    }

}
