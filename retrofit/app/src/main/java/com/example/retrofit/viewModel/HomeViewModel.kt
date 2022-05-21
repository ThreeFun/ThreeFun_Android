package com.example.retrofit.viewModel

import android.content.Context
import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import com.example.retrofit.src.data.models.team.list.TeamNearByListResponseModel
import com.example.retrofit.util.Token
import com.example.retrofit.util.TokenManager
import com.example.retrofit.util.network.MainNetworkUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    //옵저버가 가능한 상태로 만듦
    val nearByFunList: ObservableArrayList<TeamNearByListResponseModel> = ObservableArrayList()

    //GET 은 여기서도 데이터를 가져올 수 있다. POST 는 그 자체에서 넣어주자.
    fun getMainList(context: Context) {
        //IO는 backGround 작업
        CoroutineScope(Dispatchers.IO).launch {
            //데이터 통신을 하고
            val result = MainNetworkUtil.api.getMainList(TokenManager(context).getIdx()).execute()
            if (result.isSuccessful) {
                //성공
//                nearByFunList.addAll(result.body()?.result?.regionList!!)
            } else {
                //실패
            }
        }
    }
}