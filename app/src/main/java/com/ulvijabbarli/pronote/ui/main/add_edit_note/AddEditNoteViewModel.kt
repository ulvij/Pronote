package com.ulvijabbarli.pronote.ui.main.add_edit_note

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.ulvijabbarli.pronote.data.Note
import com.ulvijabbarli.pronote.data.Resource
import com.ulvijabbarli.pronote.data.source.NoteRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddEditNoteViewModel @Inject constructor(
    var repository: NoteRepository
) : ViewModel() {

    var liveNote: MediatorLiveData<Resource<Boolean>> = MediatorLiveData()
    var insertNoteCompositeDisposable: CompositeDisposable = CompositeDisposable()


    companion object {
        val TAG = AddEditNoteViewModel::class.qualifiedName
    }

    init {
        Log.e(TAG,"Notes view model is working")
    }

    override fun onCleared() {
        super.onCleared()
        insertNoteCompositeDisposable.dispose()
    }

    fun insertNote(title: String?, description: String?) {
        liveNote.value = Resource.Loading
        val note = Note(
            title = title,
            description = description
        )
        val validation = note.isValid()
        // check validation of fields
        if (!validation.first) {
            liveNote.value =
                Resource.Error(Exception(validation.second ?: "Validation Error"))
            return
        }

        // after validation
        insertNoteCompositeDisposable.add(repository.insertNote(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    liveNote.value = Resource.Success(true)
                },
                { error ->
                    liveNote.value =
                        Resource.Error(error as Exception)
                }
            )
        )

    }

}