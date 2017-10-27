package com.example.maiduan.android_project_2.sdk.utils;
//
//import android.os.AsyncTask;
//
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.PolylineOptions;
//import com.google.gson.annotations.Expose;
//import com.xtel.project.model.entity.Error;
//import com.xtel.project.model.entity.RESP_Basic;
//import com.xtel.sdk.callback.ResponseHandle;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
///**
// * Created by Vulcl on 4/10/2017
// */
//
//public class DirectionUtils {
//    private static DirectionUtils instance;
//    //    Google map
//    private final String POLY_HTTP = "https://maps.googleapis.com/maps/api/directions/json?origin=";
//    private final String POLY_DESTINATION = "&destination=";
//
//    public static DirectionUtils getInstance() {
//        if (instance == null)
//            instance = new DirectionUtils();
//        return instance;
//    }
//
//    public void getPolyLine(final double from_lat, final double from_lng, double to_lat, double to_lng, final CallbackListener callbackListener) {
//        String url = POLY_HTTP + from_lat + "," + from_lng + POLY_DESTINATION + to_lat + "," + to_lng;
//        new GetToServer(new ResponseHandle<RESP_Router>(RESP_Router.class) {
//            @Override
//            public void onSuccess(RESP_Router obj) {
//                if (obj != null) {
//                    PolylineOptions polylineOptions = getPolylineOption(obj.getRoutes().get(0).getLegs().get(0).getSteps());
//
//                    if (polylineOptions != null)
//                        callbackListener.onGetPolylineSuccess(polylineOptions);
//                    else
//                        callbackListener.onGetPolylineError();
//                }
//            }
//
//            @Override
//            public void onSuccess() {
//
//            }
//
//            @Override
//            public void onError(Error error) {
//                callbackListener.onGetPolylineError();
//            }
//        }).execute(url);
//    }
//
//    private PolylineOptions getPolylineOption(ArrayList<Steps> steps) {
//        try {
//            PolylineOptions polylineOptions = new PolylineOptions();
//
//            for (int i = 0; i < steps.size(); i++) {
//                List<LatLng> poly = decodePoly(steps.get(i).getPolyline().getPoints());
//
//                for (int j = 0; j < poly.size(); j++) {
//                    polylineOptions.add(poly.get(j));
//                }
//            }
//
//            return polylineOptions;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    private List<LatLng> decodePoly(String encoded) {
//
//        List<LatLng> poly = new ArrayList<>();
//        int index = 0, len = encoded.length();
//        int lat = 0, lng = 0;
//
//        while (index < len) {
//            int b, shift = 0, result = 0;
//            do {
//                b = encoded.charAt(index++) - 63;
//                result |= (b & 0x1f) << shift;
//                shift += 5;
//            } while (b >= 0x20);
//            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
//            lat += dlat;
//
//            shift = 0;
//            result = 0;
//            do {
//                b = encoded.charAt(index++) - 63;
//                result |= (b & 0x1f) << shift;
//                shift += 5;
//            } while (b >= 0x20);
//            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
//            lng += dlng;
//
//            LatLng p = new LatLng((((double) lat / 1E5)),
//                    (((double) lng / 1E5)));
//            poly.add(p);
//        }
//
//        return poly;
//    }
//
//
//    private interface CallbackListener {
//        void onGetPolylineSuccess(PolylineOptions polylineOptions);
//
//        void onGetPolylineError();
//    }
//
//    private class GetToServer extends AsyncTask<String, Integer, String> {
//        private ResponseHandle responseHandle;
//
//        GetToServer(ResponseHandle responseHandle) {
//            this.responseHandle = responseHandle;
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            try {
//                OkHttpClient client = new OkHttpClient();
//
//                Request.Builder builder = new Request.Builder();
//                builder.url(params[0]);
//
//                Request request = builder.build();
//
//                Response response = client.newCall(request).execute();
//                return response.body().string();
//            } catch (IOException e) {
//                return null;
//            }
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            responseHandle.onSuccess(s);
//        }
//    }
//
//
//    private class RESP_Router extends RESP_Basic {
//        @Expose
//        private ArrayList<Routers> routes;
//
//        private ArrayList<Routers> getRoutes() {
//            return routes;
//        }
//
//        public void setRoutes(ArrayList<Routers> routes) {
//            this.routes = routes;
//        }
//    }
//
//    private class Routers {
//        @Expose
//        private ArrayList<Legs> legs;
//
//        private ArrayList<Legs> getLegs() {
//            return legs;
//        }
//
//        private void setLegs(ArrayList<Legs> legs) {
//            this.legs = legs;
//        }
//    }
//
//    private class Legs {
//        @Expose
//        private ArrayList<Steps> steps;
//
//        private ArrayList<Steps> getSteps() {
//            return steps;
//        }
//
//        private void setSteps(ArrayList<Steps> steps) {
//            this.steps = steps;
//        }
//    }
//
//    private class Steps {
//        @Expose
//        private StartLocation start_location;
//        @Expose
//        private EndLocation end_location;
//        @Expose
//        private Polyline polyline;
//
//        private StartLocation getStart_location() {
//            return start_location;
//        }
//
//        private void setStart_location(StartLocation start_location) {
//            this.start_location = start_location;
//        }
//
//        private EndLocation getEnd_location() {
//            return end_location;
//        }
//
//        private void setEnd_location(EndLocation end_location) {
//            this.end_location = end_location;
//        }
//
//        private Polyline getPolyline() {
//            return polyline;
//        }
//
//        private void setPolyline(Polyline polyline) {
//            this.polyline = polyline;
//        }
//    }
//
//    private class StartLocation {
//        @Expose
//        private Double lat;
//        @Expose
//        private Double lng;
//
//        private Double getLat() {
//            return lat;
//        }
//
//        private void setLat(Double lat) {
//            this.lat = lat;
//        }
//
//        private Double getLng() {
//            return lng;
//        }
//
//        private void setLng(Double lng) {
//            this.lng = lng;
//        }
//    }
//
//    private class EndLocation {
//        @Expose
//        private Double lat;
//        @Expose
//        private Double lng;
//
//        private Double getLat() {
//            return lat;
//        }
//
//        private void setLat(Double lat) {
//            this.lat = lat;
//        }
//
//        private Double getLng() {
//            return lng;
//        }
//
//        private void setLng(Double lng) {
//            this.lng = lng;
//        }
//    }
//
//    private class Polyline {
//        @Expose
//        private String points;
//
//        private String getPoints() {
//            return points;
//        }
//
//        private void setPoints(String points) {
//            this.points = points;
//        }
//    }
//}
