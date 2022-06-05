package com.risingstar.talentaachiva.feature.participant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.risingstar.talentaachiva.domain.data.Assignment
import com.risingstar.talentaachiva.domain.data.References.ASSIGNMENT
import com.risingstar.talentaachiva.domain.data.References.SUBMISSION
import com.risingstar.talentaachiva.domain.data.Submissions

class ParticipantVM(private val assignmentId: String) : ViewModel() {
    private val db = Firebase.firestore
    private val assignmentRef = db.collection(ASSIGNMENT)
    private val submissionRef = db.collection(SUBMISSION)

    private val _assignment = MutableLiveData<Assignment?>()
    fun assignment() : LiveData<Assignment?> {
        return _assignment
    }

    private fun getAssignment(){
        assignmentRef.document(assignmentId).get().addOnCompleteListener {
            if(it.isSuccessful){
                _assignment.value = it.result.toObject()
            }
        }
    }

    private fun submitSubmission(submission:Submissions){
        submissionRef.add(submission)
    }

}

class ParticipantFactory(
    private val assignmentId:String
): ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ParticipantVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ParticipantVM(assignmentId) as T
        }
        throw IllegalArgumentException()
    }
}