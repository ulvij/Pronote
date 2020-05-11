package com.ulvijabbarli.pronote.ui.main.add_note

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.ulvijabbarli.pronote.data.DataHelper
import com.ulvijabbarli.pronote.data.model.Note
import com.ulvijabbarli.pronote.ui.main.MainResource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddNoteViewModel @Inject constructor(
    var dataHelper: DataHelper
) : ViewModel() {

    var liveNote: MediatorLiveData<MainResource<Boolean>> = MediatorLiveData()
    var insertNoteCompositeDisposable: CompositeDisposable = CompositeDisposable()


    companion object {
        val TAG = AddNoteViewModel::class.qualifiedName
    }

    init {
        Log.e(TAG,"Notes view model is working")
    }

    override fun onCleared() {
        super.onCleared()
        insertNoteCompositeDisposable.dispose()
    }

    fun insertNote(title: String?, description: String?) {
        liveNote.value = MainResource.Loading()
        val note = Note(title = title, description = description)
        val validation = note.isValid()
        Log.e("LOG_NOTE-->",validation.second?:"SALAM")
        // check validation of fields
        if (!validation.first) {
            liveNote.value =
                MainResource.Error(validation.second ?: "Validation Error", validation.first)
            return
        }

        // after validation
        insertNoteCompositeDisposable.add(dataHelper.insertNote(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    liveNote.value = MainResource.Success(true)
                },
                { error ->
                    liveNote.value =
                        MainResource.Error(error.message ?: "Something went wrong!")
                }
            )
        )

    }

}