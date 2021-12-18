package com.example.healthycollege.ui.auth

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healthycollege.utils.NetworkStatus
import androidx.lifecycle.viewModelScope
import com.example.healthycollege.data.model.LoginDTO
import com.example.healthycollege.data.model.TokenDTO
import com.example.healthycollege.data.service.TokenApiService
import com.example.healthycollege.data.service.UserApiService
import com.example.healthycollege.utils.MyApplication
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.launch

class AuthViewModel() : ViewModel() {

    //token
    var myToken = ""

    // login field
    var signInEmail = ObservableField<String>()
    var signInPassword = ObservableField<String>()
    var isSelected = ObservableField<Boolean>()

    // auth listener
    var authSignInListener: AuthListener? = null
    var networkErrorString = "네트워크 연결을 확인해 주세요."


    // 로그인 필드 확인
    fun checkLoginField() {
        Log.d("AuthViewModel","in checkLoginField")

        if(signInEmail.get().isNullOrEmpty()){
            authSignInListener?.onFailure("이메일을 입력해주세요",0)
            return
        }
        if(signInPassword.get().isNullOrEmpty()){
            authSignInListener?.onFailure("비밀번호를 입력해주세요",1)
            return
        }

        postSignIn()
    }

    // 로그인
    private val _signInResponse : MutableLiveData<String> = MutableLiveData()
    val signInResponse : LiveData<String> = _signInResponse

    private val _signInLoading = MutableLiveData<Boolean>()
    val signInLoading: LiveData<Boolean> get() = _signInLoading

    private fun postSignIn() = viewModelScope.launch {
        if(NetworkStatus.status){
            _signInLoading.postValue(true)

            _signInResponse.value = UserApiService.instance.login(
                LoginDTO(signInEmail.get()!!, signInPassword.get()!!)
            )

            _signInLoading.postValue(false)
        }
        else{
            Log.d("networkStatus","in AuthViewModel " + NetworkStatus.status.toString())
            authSignInListener?.onFailure("네트워크 연결을 확인해 주세요.",99)
        }

    }

    // fcm 토큰 발급
    private val _tokenResponse : MutableLiveData<String> = MutableLiveData()
    val tokenResponse : LiveData<String> get() = _tokenResponse

    fun getFCMToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("AuthViewModel", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            val token = task.result
            Log.d("AuthViewModel", token!!)

            myToken = token
            sendToken()
        })
    }

    private fun sendToken() = viewModelScope.launch {
        if (NetworkStatus.status) {
            Log.d("AuthViewModel", "token after response: $myToken")
            _tokenResponse.value = TokenApiService.instance.sendFirebaseToken(TokenDTO(myToken))
        } else {
            authSignInListener?.onFailure(networkErrorString,99)
        }
    }
}