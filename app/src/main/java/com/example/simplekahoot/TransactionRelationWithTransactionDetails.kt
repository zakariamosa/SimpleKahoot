package com.example.simplekahoot

import androidx.room.Embedded
import androidx.room.Relation

class TransactionRelationWithTransactionDetails (
    @Embedded
    val transaction:Transaction,
    @Relation(
        parentColumn = "transactionId",
        entityColumn = "transactionId"
    )
    val transactionDetails:List<TransactionDetails>
)