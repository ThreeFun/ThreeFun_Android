package com.example.retrofit.util

import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide.init

/**
 * 권한 요청, 위치권한
 */

//class GetPermission {
//    private fun checkPermission() {
//        //권한 확인 됨 -> 위치 잡고 정상적인 액티비티 나옴
//        if (ContextCompat.checkSelfPermission(
//                this,
//                android.Manifest.permission.ACCESS_FINE_LOCATION
//            ) == PackageManager.PERMISSION_GRANTED &&
//            ContextCompat.checkSelfPermission(
//                this,
//                android.Manifest.permission.ACCESS_COARSE_LOCATION
//            ) == PackageManager.PERMISSION_GRANTED
//        ) {
//            init()
//        }
//        //권한이 없음 -> 권한 요청
//        else {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(
//                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
//                    android.Manifest.permission.ACCESS_FINE_LOCATION,
//                ),
//                1
//            )
//        }
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        //권한이 거부
//        if (requestCode == 1) {
//            permissionCheck =
//                grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
//            init()
//        }
//    }
//}