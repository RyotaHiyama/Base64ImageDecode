package com.nokopi.base64imagedecode

import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var productImage : Bitmap

        button.setOnClickListener {
            var decodeImage = Base64.decode(editText.text.toString(), Base64.DEFAULT)
            productImage = BitmapFactory.decodeByteArray(decodeImage, 0 ,decodeImage.size)
            imageview.setImageBitmap(productImage)
            imageEncodeText.setText(imageEncode(productImage))
        }
    }

    private fun imageEncode(image :Bitmap) :String{
        val imagebit = image
        val baos = ByteArrayOutputStream()
        imagebit.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        val imageEncoded = Base64.encodeToString(b, Base64.NO_WRAP)
        return imageEncoded
    }

//    override fun onResume() {
//        super.onResume()
//        saveButton.setOnClickListener {
//            if (ContextCompat.checkSelfPermission(applicationContext, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED){
//                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),1)
//            } else {
//                saveFile(createFile())
//            }
//        }
//    }
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        saveFile(createFile())
//    }
//
//    private fun createFile() :File {
//        val dir = Environment.getExternalStorageState(File(Environment.DIRECTORY_DCIM))
//        return File(dir, "pic.jpeg")
//    }
//
//    private fun saveFile(f: File) {
//        val bit = BitmapFactory.decodeResource(resources, R.id.imageview)
//        val ops = FileOutputStream(f)
//
//        bit.compress(Bitmap.CompressFormat.PNG, 100, ops)
//        ops.close()
//
//        val contentValues = ContentValues().apply {
//            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
//            put("_data", f.absolutePath)
//        }
//        contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
//    }
}