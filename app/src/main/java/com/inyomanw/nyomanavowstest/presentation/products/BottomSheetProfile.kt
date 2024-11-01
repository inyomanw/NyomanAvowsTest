package com.inyomanw.nyomanavowstest.presentation.products

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.inyomanw.nyomanavowstest.data.room.table.UserWithAddress
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetProfile(userWithAddress: UserWithAddress) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var showModalSheet by remember { mutableStateOf(true) }


    if (showModalSheet) {
        ModalBottomSheet(
            onDismissRequest = { showModalSheet = false },
            sheetState = sheetState,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "UserName : ${userWithAddress.user.username}")
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "First Name : ${userWithAddress.user.firstName}")
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Last Name : ${userWithAddress.user.lastName}")
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Phone Number : ${userWithAddress.user.phone}")
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Email : ${userWithAddress.user.email}")
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Address : ${userWithAddress.address.street} ${userWithAddress.address.city}")

            }
        }
    }

}