package com.github.panpf.android.compose.samples.ui.components.material3

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem3
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.Material3ComposeAppBarFragment
import com.github.panpf.android.compose.samples.ui.base.MyColor
import com.github.panpf.tools4a.toast.ktx.showShortToast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class TextFieldFragment : Material3ComposeAppBarFragment() {

    override fun getTitle(): String {
        return "TextField - Material3"
    }

    @Composable
    override fun DrawContent() {
        ExpandableLayout { allExpandFlow ->
            TextFieldSample(allExpandFlow)
            TextFieldDefaultValueSample(allExpandFlow)
            TextFieldEnabledFalseSample(allExpandFlow)
            TextFieldReadOnlySample(allExpandFlow)
            TextFieldTextStyleColorSample(allExpandFlow)
            TextFieldLabelSample(allExpandFlow)
            TextFieldPlaceholderSample(allExpandFlow)
            TextFieldLeadingIconSample(allExpandFlow)
            TextFieldTrailingIconSample(allExpandFlow)
            TextFieldIsErrorSample(allExpandFlow)
            TextFieldVisualTransformationSample(allExpandFlow)
            TextFieldKeyboardOptionsKeyboardTypeSample(allExpandFlow)
            TextFieldKeyboardOptionsImeActionSample(allExpandFlow)
            TextFieldKeyboardActionsSample(allExpandFlow)
            TextFieldSingleLineSample(allExpandFlow)
            TextFieldMaxLinesSample(allExpandFlow)
            TextFieldShapeSample(allExpandFlow)
            TextFieldColorsSample(allExpandFlow)
            OutlinedTextFieldSample(allExpandFlow)
            OutlinedTextFieldLabelSample(allExpandFlow)
            OutlinedTextFieldIsErrorSample(allExpandFlow)
            OutlinedTextFieldShapeSample(allExpandFlow)
            OutlinedTextFieldColorsSample(allExpandFlow)
        }
    }
}

private const val novel =
    "躲过了暴风雪之后，我们再次起程赶路，在一处斜坡下发现了阿宁他们的马队，同时也发现了海底墓穴影画之中的那一座神秘雪山，赫然出现在了我们的视野尽头。就在我们询问向导如何才能到达那里的时候，顺子却摇头，说我们绝对无法过去。"


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextFieldSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf("") }
    ExpandableItem3(title = "TextField", allExpandFlow, padding = 20.dp) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = { inputText.value = it }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextFieldSamplePreview() {
    TextFieldSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextFieldDefaultValueSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf(novel) }
    ExpandableItem3(title = "TextField（value）", allExpandFlow, padding = 20.dp) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = { inputText.value = it }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextFieldDefaultValueSamplePreview() {
    TextFieldDefaultValueSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextFieldEnabledFalseSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf("") }
    ExpandableItem3(title = "TextField（enabled - false）", allExpandFlow, padding = 20.dp) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = { inputText.value = it },
            enabled = false,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextFieldEnabledFalseSamplePreview() {
    TextFieldEnabledFalseSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextFieldReadOnlySample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf(novel) }
    ExpandableItem3(title = "TextField（readOnly）", allExpandFlow, padding = 20.dp) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = { inputText.value = it },
            readOnly = true,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextFieldReadOnlySamplePreview() {
    TextFieldReadOnlySample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextFieldTextStyleColorSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf(novel) }
    ExpandableItem3(title = "TextField（textStyle - color）", allExpandFlow, padding = 20.dp) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = { inputText.value = it },
            textStyle = TextStyle(color = Color.Magenta),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextFieldTextStyleColorSamplePreview() {
    TextFieldTextStyleColorSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextFieldLabelSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf("") }
    ExpandableItem3(title = "TextField（label）", allExpandFlow, padding = 20.dp) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = { inputText.value = it },
            label = { Text(text = "请输入你想对 TA 说的话") }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextFieldLabelSamplePreview() {
    TextFieldLabelSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextFieldPlaceholderSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf("") }
    ExpandableItem3(title = "TextField（placeholder）", allExpandFlow, padding = 20.dp) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = { inputText.value = it },
            placeholder = { Text(text = "请输入你想对 TA 说的话") }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextFieldPlaceholderSamplePreview() {
    TextFieldPlaceholderSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextFieldLeadingIconSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf("") }
    ExpandableItem3(title = "TextField（leadingIcon）", allExpandFlow, padding = 20.dp) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = { inputText.value = it },
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_phone),
                    contentDescription = ""
                )
            }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextFieldLeadingIconSamplePreview() {
    TextFieldLeadingIconSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextFieldTrailingIconSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf("") }
    ExpandableItem3(title = "TextField（trailingIcon）", allExpandFlow, padding = 20.dp) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = { inputText.value = it },
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        inputText.value = ""
                    }
                )
            }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextFieldTrailingIconSamplePreview() {
    TextFieldTrailingIconSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextFieldIsErrorSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf("") }
    val isError = remember { mutableStateOf(false) }
    ExpandableItem3(title = "TextField（isError）", allExpandFlow, padding = 20.dp) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = {
                inputText.value = it
                isError.value = it.any { char -> !char.isDigit() }
            },
            label = { Text(text = "请输入手机号") },
            isError = isError.value,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextFieldIsErrorSamplePreview() {
    TextFieldIsErrorSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextFieldVisualTransformationSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf("") }
    ExpandableItem3(
        title = "TextField（visualTransformation - Password）",
        allExpandFlow,
        padding = 20.dp
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = { inputText.value = it },
            label = { Text(text = "请输入密码") },
            visualTransformation = PasswordVisualTransformation(),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextFieldVisualTransformationSamplePreview() {
    TextFieldVisualTransformationSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextFieldKeyboardOptionsKeyboardTypeSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf("") }
    ExpandableItem3(
        title = "TextField（keyboardOptions - Number）",
        allExpand = allExpandFlow,
        20.dp
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = { inputText.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextFieldKeyboardOptionsKeyboardTypeSamplePreview() {
    TextFieldKeyboardOptionsKeyboardTypeSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextFieldKeyboardOptionsImeActionSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf("") }
    ExpandableItem3(title = "TextField（keyboardOptions - Search）", allExpandFlow, padding = 20.dp) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = { inputText.value = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextFieldKeyboardOptionsImeActionSamplePreview() {
    TextFieldKeyboardOptionsImeActionSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextFieldKeyboardActionsSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    val inputText = remember { mutableStateOf("") }
    ExpandableItem3(title = "TextField（keyboardActions）", allExpandFlow, padding = 20.dp) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = { inputText.value = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { context.showShortToast("点击了完成！") }),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextFieldKeyboardActionsSamplePreview() {
    TextFieldKeyboardActionsSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextFieldSingleLineSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf(novel) }
    ExpandableItem3(title = "TextField（singleLine）", allExpandFlow, padding = 20.dp) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = { inputText.value = it },
            singleLine = true
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextFieldSingleLineSamplePreview() {
    TextFieldSingleLineSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextFieldMaxLinesSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf(novel) }
    ExpandableItem3(title = "TextField（maxLines - 2）", allExpandFlow, padding = 20.dp) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = { inputText.value = it },
            maxLines = 2
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextFieldMaxLinesSamplePreview() {
    TextFieldMaxLinesSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextFieldShapeSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf(novel) }
    ExpandableItem3(title = "TextField（shape）", allExpandFlow, padding = 20.dp) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = { inputText.value = it },
            shape = RoundedCornerShape(size = 100.dp),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextFieldShapeSamplePreview() {
    TextFieldShapeSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextFieldColorsSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf(novel) }
    ExpandableItem3(title = "TextField（colors）", allExpandFlow, padding = 20.dp) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = { inputText.value = it },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MyColor.TranslucenceBlue,
                textColor = Color.White,
                cursorColor = Color.Cyan
            ),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TextFieldColorsSamplePreview() {
    TextFieldColorsSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OutlinedTextFieldSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf("") }
    ExpandableItem3(title = "OutlinedTextField", allExpandFlow, padding = 20.dp) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = { inputText.value = it }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun OutlinedTextFieldSamplePreview() {
    OutlinedTextFieldSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OutlinedTextFieldLabelSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf("") }
    ExpandableItem3(title = "OutlinedTextField（label）", allExpandFlow, padding = 20.dp) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = { inputText.value = it },
            label = { Text(text = "请输入你想对 TA 说的话") }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun OutlinedTextFieldLabelSamplePreview() {
    OutlinedTextFieldLabelSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OutlinedTextFieldIsErrorSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf("") }
    val isError = remember { mutableStateOf(false) }
    ExpandableItem3(title = "OutlinedTextField（isError）", allExpandFlow, padding = 20.dp) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = {
                inputText.value = it
                isError.value = it.any { char -> !char.isDigit() }
            },
            label = { Text(text = "请输入手机号") },
            isError = isError.value,
            colors = TextFieldDefaults.outlinedTextFieldColors(errorBorderColor = Color.Red)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun OutlinedTextFieldIsErrorSamplePreview() {
    OutlinedTextFieldIsErrorSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OutlinedTextFieldShapeSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf(novel) }
    ExpandableItem3(title = "OutlinedTextField（shape）", allExpandFlow, padding = 20.dp) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = { inputText.value = it },
            shape = RoundedCornerShape(size = 100.dp),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun OutlinedTextFieldShapeSamplePreview() {
    OutlinedTextFieldShapeSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OutlinedTextFieldColorsSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf(novel) }
    ExpandableItem3(
        title = "OutlinedTextField（colors）",
        allExpand = allExpandFlow
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = { inputText.value = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = MyColor.TranslucenceBlue,
                textColor = Color.White,
                cursorColor = Color.Cyan,
                unfocusedBorderColor = Color.Green,
                focusedBorderColor = Color.Yellow
            ),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun OutlinedTextFieldColorsSamplePreview() {
    OutlinedTextFieldColorsSample(remember { MutableStateFlow(true) })
}