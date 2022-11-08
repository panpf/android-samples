package com.github.panpf.android.compose.samples.ui.material3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.panpf.android.compose.samples.R
import com.github.panpf.android.compose.samples.ui.base.ExpandableItem
import com.github.panpf.android.compose.samples.ui.base.ExpandableLayout
import com.github.panpf.android.compose.samples.ui.base.ToolbarFragment
import com.github.panpf.android.compose.samples.ui.base.theme.MyTheme3
import com.github.panpf.tools4a.toast.ktx.showShortToast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class TextFieldFragment : ToolbarFragment() {

    override fun createView(
        toolbar: Toolbar,
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolbar.title = "TextField"
        return ComposeView(inflater.context).apply {
            setContent {
                MyTheme3 {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
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
                            TextFieldColorsContainerColorSample(allExpandFlow)
                            TextFieldColorsCursorColorSample(allExpandFlow)
                            OutlinedTextFieldSample(allExpandFlow)
                            OutlinedTextFieldLabelSample(allExpandFlow)
                            OutlinedTextFieldIsErrorSample(allExpandFlow)
                            OutlinedTextFieldShapeSample(allExpandFlow)
                            OutlinedTextFieldColorsContainerColorSample(allExpandFlow)
                            OutlinedTextFieldColorsBorderColorSample(allExpandFlow)
                        }
                    }
                }
            }
        }
    }
}

private const val novel =
    "躲过了暴风雪之后，我们再次起程赶路，在一处斜坡下发现了阿宁他们的马队，同时也发现了海底墓穴影画之中的那一座神秘雪山，赫然出现在了我们的视野尽头。就在我们询问向导如何才能到达那里的时候，顺子却摇头，说我们绝对无法过去。"


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf("") }
    ExpandableItem(title = "TextField", allExpandFlow, padding = 20.dp) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = { inputText.value = it }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun TextFieldSamplePreview() {
    TextFieldSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldDefaultValueSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf(novel) }
    ExpandableItem(title = "TextField（value）", allExpandFlow, padding = 20.dp) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = { inputText.value = it }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun TextFieldDefaultValueSamplePreview() {
    TextFieldDefaultValueSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldEnabledFalseSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf("") }
    ExpandableItem(title = "TextField（enabled - false）", allExpandFlow, padding = 20.dp) {
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
fun TextFieldEnabledFalseSamplePreview() {
    TextFieldEnabledFalseSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldReadOnlySample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf(novel) }
    ExpandableItem(title = "TextField（readOnly）", allExpandFlow, padding = 20.dp) {
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
fun TextFieldReadOnlySamplePreview() {
    TextFieldReadOnlySample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldTextStyleColorSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf(novel) }
    ExpandableItem(title = "TextField（textStyle - color）", allExpandFlow, padding = 20.dp) {
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
fun TextFieldTextStyleColorSamplePreview() {
    TextFieldTextStyleColorSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldLabelSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf("") }
    ExpandableItem(title = "TextField（label）", allExpandFlow, padding = 20.dp) {
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
fun TextFieldLabelSamplePreview() {
    TextFieldLabelSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldPlaceholderSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf("") }
    ExpandableItem(title = "TextField（placeholder）", allExpandFlow, padding = 20.dp) {
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
fun TextFieldPlaceholderSamplePreview() {
    TextFieldPlaceholderSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldLeadingIconSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf("") }
    ExpandableItem(title = "TextField（leadingIcon）", allExpandFlow, padding = 20.dp) {
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
fun TextFieldLeadingIconSamplePreview() {
    TextFieldLeadingIconSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldTrailingIconSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf("") }
    ExpandableItem(title = "TextField（trailingIcon）", allExpandFlow, padding = 20.dp) {
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
fun TextFieldTrailingIconSamplePreview() {
    TextFieldTrailingIconSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldIsErrorSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf("") }
    val isError = remember { mutableStateOf(false) }
    ExpandableItem(title = "TextField（isError）", allExpandFlow, padding = 20.dp) {
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
fun TextFieldIsErrorSamplePreview() {
    TextFieldIsErrorSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldVisualTransformationSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf("") }
    ExpandableItem(title = "TextField（visualTransformation）", allExpandFlow, padding = 20.dp) {
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
fun TextFieldVisualTransformationSamplePreview() {
    TextFieldVisualTransformationSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldKeyboardOptionsKeyboardTypeSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf("") }
    ExpandableItem(
        title = "TextField（keyboardOptions - 只能输入数字）",
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
fun TextFieldKeyboardOptionsKeyboardTypeSamplePreview() {
    TextFieldKeyboardOptionsKeyboardTypeSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldKeyboardOptionsImeActionSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf("") }
    ExpandableItem(title = "TextField（keyboardOptions - 搜索）", allExpandFlow, padding = 20.dp) {
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
fun TextFieldKeyboardOptionsImeActionSamplePreview() {
    TextFieldKeyboardOptionsImeActionSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldKeyboardActionsSample(allExpandFlow: Flow<Boolean>) {
    val context = LocalContext.current
    val inputText = remember { mutableStateOf("") }
    ExpandableItem(title = "TextField（keyboardActions）", allExpandFlow, padding = 20.dp) {
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
fun TextFieldKeyboardActionsSamplePreview() {
    TextFieldKeyboardActionsSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldSingleLineSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf(novel) }
    ExpandableItem(title = "TextField（singleLine）", allExpandFlow, padding = 20.dp) {
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
fun TextFieldSingleLineSamplePreview() {
    TextFieldSingleLineSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldMaxLinesSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf(novel) }
    ExpandableItem(title = "TextField（maxLines - 2）", allExpandFlow, padding = 20.dp) {
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
fun TextFieldMaxLinesSamplePreview() {
    TextFieldMaxLinesSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldShapeSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf(novel) }
    ExpandableItem(title = "TextField（shape）", allExpandFlow, padding = 20.dp) {
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
fun TextFieldShapeSamplePreview() {
    TextFieldShapeSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldColorsContainerColorSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf(novel) }
    ExpandableItem(title = "TextField（colors - containerColor）", allExpandFlow, padding = 20.dp) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = { inputText.value = it },
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.Gray),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun TextFieldColorsContainerColorSamplePreview() {
    TextFieldColorsContainerColorSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldColorsCursorColorSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf(novel) }
    ExpandableItem(title = "TextField（colors - cursorColor）", allExpandFlow, padding = 20.dp) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = { inputText.value = it },
            colors = TextFieldDefaults.textFieldColors(cursorColor = Color.Cyan),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun TextFieldColorsCursorColorSamplePreview() {
    TextFieldColorsCursorColorSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf("") }
    ExpandableItem(title = "OutlinedTextField", allExpandFlow, padding = 20.dp) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = { inputText.value = it }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun OutlinedTextFieldSamplePreview() {
    OutlinedTextFieldSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldLabelSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf("") }
    ExpandableItem(title = "OutlinedTextField（label）", allExpandFlow, padding = 20.dp) {
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
fun OutlinedTextFieldLabelSamplePreview() {
    OutlinedTextFieldLabelSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldIsErrorSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf("") }
    val isError = remember { mutableStateOf(false) }
    ExpandableItem(title = "OutlinedTextField（isError）", allExpandFlow, padding = 20.dp) {
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
fun OutlinedTextFieldIsErrorSamplePreview() {
    OutlinedTextFieldIsErrorSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldShapeSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf(novel) }
    ExpandableItem(title = "OutlinedTextField（shape）", allExpandFlow, padding = 20.dp) {
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
fun OutlinedTextFieldShapeSamplePreview() {
    OutlinedTextFieldShapeSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldColorsContainerColorSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf(novel) }
    ExpandableItem(
        title = "OutlinedTextField（colors - containerColor）",
        allExpand = allExpandFlow
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = { inputText.value = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.Gray),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun OutlinedTextFieldColorsContainerColorSamplePreview() {
    OutlinedTextFieldColorsContainerColorSample(remember { MutableStateFlow(true) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldColorsBorderColorSample(allExpandFlow: Flow<Boolean>) {
    val inputText = remember { mutableStateOf(novel) }
    ExpandableItem(
        title = "OutlinedTextField（colors - borderColor）",
        allExpand = allExpandFlow,
        20.dp
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText.value,
            onValueChange = { inputText.value = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Green,
                focusedBorderColor = Color.Yellow
            ),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun OutlinedTextFieldColorsBorderColorSamplePreview() {
    OutlinedTextFieldColorsBorderColorSample(remember { MutableStateFlow(true) })
}