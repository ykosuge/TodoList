package com.sonix.todoapp.model.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*

object DateSerializer: KSerializer<Date> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder) = formatter.parse(decoder.decodeString()) ?: throw IllegalStateException()
    override fun serialize(encoder: Encoder, value: Date)  = encoder.encodeString(formatter.format(value))

    private val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.JAPAN)
}
