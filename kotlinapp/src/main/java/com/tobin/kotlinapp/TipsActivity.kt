package com.tobin.kotlinapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview

/**
 * @author lijunbin
 * @date 2020/9/29
 * @description Tips
 */
class TipsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            tips()
        }
    }
}


@Preview
@Composable
fun tips() {
    MaterialTheme(){
        PaddingValues(10.dp)
        Column {
            Text(
                text = "Text 控件使用",
                overflow = TextOverflow.Ellipsis,
//                maxLines = 1,
                style = TextStyle(Color.Black,
                    fontSize = TextUnit.Companion.Sp(12f),
                    fontStyle = FontStyle.Italic)
            )
            Text(
                text = "1、通过copy可以复用系统样式并修改部分样式；行高等属性通过段落样式：" +
                        "ParagraphStyle进行设置；边距等可以通过modifier进行设置，" +
                        "modifier直译过来就是修饰符的意思，赋予modifier的修饰属性可以有多个，" +
                        "通过wraps进行组合",
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(Color.Blue,
                    fontSize = TextUnit.Companion.Sp(12f),
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.Default)
            )
            Text(

                text = "众鸟高飞尽，孤云独去闲。相看两不厌，只有敬亭山。",
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,

//                style = MaterialTheme.typography.body2.copy(color = Color.Blue),
//                paragraphStyle = ParagraphStyle(textAlign = TextAlign.Center,
//                    lineHeight = TextUnit.Sp(12))
                style = TextStyle(Color.Blue,
                    fontSize = TextUnit.Companion.Sp(12f),
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.Default),

            )
        }
    }

}

@Preview
@Composable
fun ArticleScreen(
    title: String,
    post: Post,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit
) {


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Published in: $title",
                        style = MaterialTheme.typography.subtitle2,
                        color = contentColor()
                    )
                }
            )
        },
        bodyContent = { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            postContent(post1, modifier)
        },

    )
}