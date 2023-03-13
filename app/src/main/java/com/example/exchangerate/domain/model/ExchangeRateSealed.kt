package com.example.exchangerate.domain.model

sealed class ExchangeRateSealed


object ConditionError: ExchangeRateSealed()
object ConditionSuccess: ExchangeRateSealed()
