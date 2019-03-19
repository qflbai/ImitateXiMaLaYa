package com.qflbai.lib.location;


import com.qflbai.lib.constant.ConstantValues;
import com.qflbai.lib.event.LiveBus;
import com.qflbai.lib.utils.log.LogUtil;

/**
 * @author WenXian Bai
 * @Date: 2018/3/23.
 * @Description: 位置信息类
 */
public class LocationInfoUtil {
    private static LocationInfo mLocationInfo;

    /**
     * 开始定位
     */
    public static void startLocation() {
        LocationManager.getInstance().start();
    }

    public static LocationInfo getLocationInfo() {
        return mLocationInfo;
    }

    /**
     * @author ZhuHongKai
     * @date 2016年6月11日
     * @Description: 保存定位信息
     */
    public static void savaLAddress(String latitude, String longtitude, String radius, String countryCode,
                                    String country, String cityCode, String city, String district, String street,
                                    String addr, String describe) {

        LocationInfo locationInfo = new LocationInfo();
        locationInfo.setLatitude(latitude);
        locationInfo.setLongtitude(longtitude);
        locationInfo.setRadius(radius);
        locationInfo.setCountryCode(countryCode);
        locationInfo.setCountry(country);
        locationInfo.setCity(city);
        locationInfo.setCityCode(cityCode);
        locationInfo.setDistrict(district);
        locationInfo.setStreet(street);
        locationInfo.setAddr(addr);
        locationInfo.setDescribe(describe);
        mLocationInfo = locationInfo;
        LiveBus.getDefault().postStickyEvent(ConstantValues.Event.KEY_LOCATION, locationInfo);
        LogUtil.w("LocationInfo","livebus");
    }

}
