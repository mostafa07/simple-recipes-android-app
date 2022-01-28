package com.example.android.simplerecipes

import com.example.android.simplerecipes.data.model.app.CustomMessage

class BusinessException(val businessMessage: CustomMessage) : Exception()