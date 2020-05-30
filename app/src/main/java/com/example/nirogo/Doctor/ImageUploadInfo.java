package com.example.nirogo.Doctor;

import android.net.Uri;

import com.google.android.gms.tasks.Task;

public class ImageUploadInfo {

    public String imageName;

    public String imageURL;

    public ImageUploadInfo(String userName, Task<Uri> downloadUrl) {

    }

    public ImageUploadInfo(String name, String url) {

        this.imageName = name;
        this.imageURL= url;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageURL() {
        return imageURL;
    }

}