package ru.surok.clientserverappproject.adapters

import android.view.View
import ru.surok.clientserverappproject.data.models.FreeSoundSound

interface ListOnClickListener {

    fun onClick(view: View, item: FreeSoundSound)
}