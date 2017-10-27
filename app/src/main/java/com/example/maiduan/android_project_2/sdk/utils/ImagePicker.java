//package com.example.maiduan.android_project_2.sdk.utils;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.ResolveInfo;
//import android.content.res.AssetFileDescriptor;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Matrix;
//import android.media.ExifInterface;
//import android.net.Uri;
//import android.os.Environment;
//import android.os.Parcelable;
//import android.provider.MediaStore;
//import android.util.Log;
//
//import com.example.maiduan.android_project_2.MyApplication;
//import com.example.maiduan.android_project_2.R;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Author: Lê Công Long Vũ
// * Date: 08/09/2015
// * Email: leconglongvu@gmail.com
// */
//public class ImagePicker {
//
//    private static final int DEFAULT_MIN_WIDTH_QUALITY = 400;        // min pixels
//    private static final String TAG = "ImagePicker";
//    private static final String TEMP_IMAGE_NAME = "tempImage";
//
//    public static int minWidthQuality = DEFAULT_MIN_WIDTH_QUALITY;
//
//
//    public static Intent getPickImageIntent(Context context) {
//        Intent chooserIntent = null;
//
//        List<Intent> intentList = new ArrayList<>();
//
//        Intent pickIntent = new Intent(Intent.ACTION_PICK,
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        takePhotoIntent.putExtra("return-data", true);
//        takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(getTempFile(context)));
//        intentList = addIntentsToList(context, intentList, pickIntent);
//        intentList = addIntentsToList(context, intentList, takePhotoIntent);
//
//        if (intentList.size() > 0) {
//            chooserIntent = Intent.createChooser(intentList.remove(intentList.size() - 1),
//                    context.getString(R.string.message_select_picture));
//            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentList.toArray(new Parcelable[]{}));
//        }
//
//        return chooserIntent;
//    }
//
//    private static List<Intent> addIntentsToList(Context context, List<Intent> list, Intent intent) {
//        List<ResolveInfo> resInfo = context.getPackageManager().queryIntentActivities(intent, 0);
//        for (ResolveInfo resolveInfo : resInfo) {
//            String packageName = resolveInfo.activityInfo.packageName;
//            Intent targetedIntent = new Intent(intent);
//            targetedIntent.setPackage(packageName);
//            list.add(targetedIntent);
//            Log.d(TAG, "Intent: " + intent.getAction() + " package: " + packageName);
//        }
//        return list;
//    }
//
//    public static byte[] convertFileToByteArray(File file) {
//        byte[] byteArray = null;
//        try {
//            InputStream inputStream = new FileInputStream(file);
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            byte[] b = new byte[1024 * 8];
//            int bytesRead = 0;
//
//            while ((bytesRead = inputStream.read(b)) != -1) {
//                bos.write(b, 0, bytesRead);
//            }
//
//            byteArray = bos.toByteArray();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return byteArray;
//    }
//
//    public static Bitmap getImageFromResult(Context context, Intent imageReturnedIntent) {
//        Log.d(TAG, "getImageFromResult, resultCode: ");
//        Bitmap bm = null;
//        File imageFile = getTempFile(context);
//
//        Uri selectedImage;
//        boolean isCamera = (imageReturnedIntent == null || imageReturnedIntent.getData() == null || imageReturnedIntent.getData().toString().contains(imageFile.toString()));
//        if (isCamera) {     /** CAMERA **/
//            selectedImage = Uri.fromFile(imageFile);
//        } else {            /** ALBUM **/
//            selectedImage = imageReturnedIntent.getData();
//        }
//        Log.d(TAG, "selectedImage: " + selectedImage);
//
//        bm = getImageResized(context, selectedImage);
//
//        int rotation = getRotation(context, selectedImage, isCamera);
//        bm = rotate(bm, rotation);
//
//        return bm;
//    }
//
//    public static File getFileFromResult(Context context, Intent imageReturnedIntent) {
//        Log.d(TAG, "getImageFromResult, resultCode: ");
//        File imageFile = getTempFile(context);
//
//        boolean isCamera = (imageReturnedIntent == null || imageReturnedIntent.getData() == null || imageReturnedIntent.getData().toString().contains(imageFile.toString()));
//        if (!isCamera) {
//            Uri selectedImage = imageReturnedIntent.getData();
//
//            String path = getRealPathFromUri(context, selectedImage);
//            if (path != null)
//                imageFile = new File(path);
//        }
//        Log.d(TAG, "selectedImage: " + imageFile.getAbsolutePath());
//
//        return imageFile;
//    }
//
//    public static Uri getUriFromResult(Context context, Intent imageReturnedIntent) {
//        Log.d(TAG, "getImageFromResult, resultCode: ");
//        File imageFile = getTempFile(context);
//
//        Uri selectedImage;
//        boolean isCamera = (imageReturnedIntent == null || imageReturnedIntent.getData() == null || imageReturnedIntent.getData().toString().contains(imageFile.toString()));
//        if (isCamera) {     /** CAMERA **/
//            selectedImage = Uri.fromFile(imageFile);
//        } else {            /** ALBUM **/
//            selectedImage = imageReturnedIntent.getData();
//        }
//        Log.d(TAG, "selectedImage: " + selectedImage);
//
//        return selectedImage;
//    }
//
//    private static String getRealPathFromUri(Context context, Uri contentUri) {
//        Cursor cursor = null;
//        try {
//            String[] proj = {MediaStore.Images.Media.DATA};
//            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
//            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//            cursor.moveToFirst();
//            return cursor.getString(column_index);
//        } finally {
//            if (cursor != null) {
//                cursor.close();
//            }
//        }
//    }
//
//    public static Bitmap scaleBitmap(Bitmap bm, int maxWidth, int maxHeight) {
//        int width = bm.getWidth();
//        int height = bm.getHeight();
//
//        if (width > height) {
//            // landscape
//            float ratio = (float) width / maxWidth;
//            width = maxWidth;
//            height = (int) (height / ratio);
//        } else if (height > width) {
//            // portrait
//            float ratio = (float) height / maxHeight;
//            height = maxHeight;
//            width = (int) (width / ratio);
//        } else {
//            // square
//            height = maxHeight;
//            width = maxWidth;
//        }
//
//        Log.v("Pictures", "after scaling Width and height are " + width + "--" + height);
//
//        bm = Bitmap.createScaledBitmap(bm, width, height, true);
//        return bm;
//    }
//
//    public static Bitmap convertFileToBitmap(File file) {
//        if (file == null)
//            return null;
//
//        return BitmapFactory.decodeFile(file.getPath());
//    }
//
//    public static byte[] convertBitmapToByteArray(Bitmap bitmap) {
//        if (bitmap == null)
//            return null;
//
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//        return stream.toByteArray();
//    }
//
//    public static byte[] convertUriToByteArray(Uri uri) {
//        if (uri == null)
//            return null;
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        FileInputStream fis;
//
//        try {
//            fis = new FileInputStream(new File(uri.getPath()));
//            byte[] buf = new byte[1024];
//            int n;
//            while (-1 != (n = fis.read(buf)))
//                baos.write(buf, 0, n);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return baos.toByteArray();
//    }
//
//    public static Bitmap convertByteArrayToBitmap(byte[] byteArray) {
//        if (byteArray == null)
//            return null;
//
//        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//    }
//
//    public static File saveBitmapToFile(Bitmap bitmap) {
//        if (bitmap == null)
//            return null;
//
//        File pictureFile = getOutputMediaFile();
//        if (pictureFile == null) {
//            return null;
//        }
//
//        try {
//            FileOutputStream fos = new FileOutputStream(pictureFile);
//            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
//            fos.close();
//            return pictureFile;
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    /**
//     * Create a File for saving an image or video
//     */
//    private static File getOutputMediaFile() {
//        // To be safe, you should check that the SDCard is mounted
//        // using Environment.getExternalStorageState() before doing this.
//        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
//                + "/Android/data/"
//                + MyApplication.context.getApplicationContext().getPackageName()
//                + "/Files");
//
//        // This location works best if you want the created images to be shared
//        // between applications and persist after your app has been uninstalled.
//
//        // Create the storage directory if it does not exist
//        if (!mediaStorageDir.exists()) {
//            if (!mediaStorageDir.mkdirs()) {
//                return null;
//            }
//        }
//        // Create a media file name
//        File mediaFile;
//        String mImageName = "XT_" + TimeUtils.getCreteTime() + ".jpg";
//        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
//        return mediaFile;
//    }
//
//    private static File getTempFile(Context context) {
//        File imageFile = new File(context.getExternalCacheDir(), TEMP_IMAGE_NAME);
//        imageFile.getParentFile().mkdirs();
//        return imageFile;
//    }
//
//    private static Bitmap decodeBitmap(Context context, Uri theUri, int sampleSize) {
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inSampleSize = sampleSize;
//
//        AssetFileDescriptor fileDescriptor = null;
//        try {
//            fileDescriptor = context.getContentResolver().openAssetFileDescriptor(theUri, "r");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        Bitmap actuallyUsableBitmap = BitmapFactory.decodeFileDescriptor(
//                fileDescriptor.getFileDescriptor(), null, options);
//
//        Log.d(TAG, options.inSampleSize + " sample method bitmap ... " +
//                actuallyUsableBitmap.getWidth() + " " + actuallyUsableBitmap.getHeight());
//
//        return actuallyUsableBitmap;
//    }
//
//    /**
//     * Resize to avoid using too much memory loading big images (e.g.: 2560*1920)
//     **/
//    private static Bitmap getImageResized(Context context, Uri selectedImage) {
//        Bitmap bm = null;
//        int[] sampleSizes = new int[]{5, 3, 2, 1};
//        int i = 0;
//        do {
//            bm = decodeBitmap(context, selectedImage, sampleSizes[i]);
//            Log.d(TAG, "resizer: new bitmap width = " + bm.getWidth());
//            i++;
//        } while (bm.getWidth() < minWidthQuality && i < sampleSizes.length);
//        return bm;
//    }
//
//
//    private static int getRotation(Context context, Uri imageUri, boolean isCamera) {
//        int rotation;
//        if (isCamera) {
//            rotation = getRotationFromCamera(context, imageUri);
//        } else {
//            rotation = getRotationFromGallery(context, imageUri);
//        }
//        Log.d(TAG, "Image rotation: " + rotation);
//        return rotation;
//    }
//
//    private static int getRotationFromCamera(Context context, Uri imageFile) {
//        int rotate = 0;
//        try {
//
//            context.getContentResolver().notifyChange(imageFile, null);
//            ExifInterface exif = new ExifInterface(imageFile.getPath());
//            int orientation = exif.getAttributeInt(
//                    ExifInterface.TAG_ORIENTATION,
//                    ExifInterface.ORIENTATION_NORMAL);
//
//            switch (orientation) {
//                case ExifInterface.ORIENTATION_ROTATE_270:
//                    rotate = 270;
//                    break;
//                case ExifInterface.ORIENTATION_ROTATE_180:
//                    rotate = 180;
//                    break;
//                case ExifInterface.ORIENTATION_ROTATE_90:
//                    rotate = 90;
//                    break;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return rotate;
//    }
//
//    public static int getRotationFromGallery(Context context, Uri imageUri) {
//        int result = 0;
//        String[] columns = {MediaStore.Images.Media.ORIENTATION};
//        Cursor cursor = null;
//        try {
//            cursor = context.getContentResolver().query(imageUri, columns, null, null, null);
//            if (cursor != null && cursor.moveToFirst()) {
//                int orientationColumnIndex = cursor.getColumnIndex(columns[0]);
//                result = cursor.getInt(orientationColumnIndex);
//            }
//        } catch (Exception e) {
//            //Do nothing
//        } finally {
//            if (cursor != null) {
//                cursor.close();
//            }
//        }//End of try-catch block
//        return result;
//    }
//
//
//    private static Bitmap rotate(Bitmap bm, int rotation) {
//        if (rotation != 0) {
//            Matrix matrix = new Matrix();
//            matrix.postRotate(rotation);
//            Bitmap bmOut = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
//            return bmOut;
//        }
//        return bm;
//    }
//}