/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tobin.kotlinapp

/**
 * Define hardcoded posts to avoid handling any non-ui operations.
 */

val pietro = PostAuthor("Pietro Maggi", "https://medium.com/@pmaggi")

val publication = Publication(
    "Android Developers",
    "https://cdn-images-1.medium.com/max/258/1*u7oZc2_5mrkcFaxkXEyfYA@2x.png"
)
val paragraphsPost1 = listOf(
    Paragraph(
        ParagraphType.Text,
        "Working to make our Android application more modular, I ended up with a sample that included a set of on-demand features grouped inside a folder:"
    ),
    Paragraph(
        ParagraphType.Text,
        "Pretty standard setup, all the on-demand modules, inside a “features” folder; clean."
    ),
    Paragraph(
        ParagraphType.Text,
        "These modules are included in the settings.gradle file as:"
    ),
    Paragraph(
        ParagraphType.CodeBlock,
        "include ':app'\n" +
            "include ':features:module1'\n" +
            "include ':features:module2'\n" +
            "include ':features:module3'\n" +
            "include ':features:module4'"
    ),
    Paragraph(
        ParagraphType.Text,
        "These setup works nicely with a single “minor” issue: an empty module named features in the Android view in Android Studio:"
    ),
    Paragraph(
        ParagraphType.Text,
        "I can live with that, but I would much prefer to remove that empty module from my project!"
    ),
    Paragraph(
        ParagraphType.Header,
        "If you cannot remove it, just rename it!"
    ),

    Paragraph(
        ParagraphType.Text,
        "At I/O I was lucky enough to attend the “Android Studio: Tips and Tricks” talk where Ivan Gravilovic, from Google, shared some amazing tips. One of these was a possible solution for my problem: setting a custom path for my modules.",
        listOf(
            Markup(
                MarkupType.Italic,
                41,
                72
            )
        )
    ),

    Paragraph(
        ParagraphType.Text,
        "In this particular case our settings.gradle becomes:",
        listOf(Markup(MarkupType.Code, 28, 43))
    ),
    Paragraph(
        ParagraphType.CodeBlock,
        """
        include ':app'
        include ':module1'
        include ':module1'
        include ':module1'
        include ':module1'
        """.trimIndent()
    ),
    Paragraph(
        ParagraphType.CodeBlock,
        """
        // Set a custom path for the four features modules.
        // This avoid to have an empty "features" module in  Android Studio.
        project(":module1").projectDir=new File(rootDir, "features/module1")
        project(":module2").projectDir=new File(rootDir, "features/module2")
        project(":module3").projectDir=new File(rootDir, "features/module3")
        project(":module4").projectDir=new File(rootDir, "features/module4")
        """.trimIndent()
    ),
    Paragraph(
        ParagraphType.Text,
        "And the layout in Android Studio is now:"
    ),
    Paragraph(
        ParagraphType.Header,
        "Conclusion"
    ),
    Paragraph(
        ParagraphType.Text,
        "As the title says, this is really a small thing, but it helps keep my project in order and it shows how a small Gradle configuration can help keep your project tidy."
    ),
    Paragraph(
        ParagraphType.Quote,
        "You can find this update in the latest version of the on-demand modules codelab.",
        listOf(
            Markup(
                MarkupType.Link,
                54,
                79,
                "https://codelabs.developers.google.com/codelabs/on-demand-dynamic-delivery/index.html"
            )
        )
    ),
    Paragraph(
        ParagraphType.Header,
        "Resources"
    ),
    Paragraph(
        ParagraphType.Bullet,
        "Android Studio: Tips and Tricks (Google I/O’19)",
        listOf(
            Markup(
                MarkupType.Link,
                0,
                47,
                "https://www.youtube.com/watch?v=ihF-PwDfRZ4&list=PLWz5rJ2EKKc9FfSQIRXEWyWpHD6TtwxMM&index=32&t=0s"
            )
        )
    ),

    Paragraph(
        ParagraphType.Bullet,
        "On Demand module codelab",
        listOf(
            Markup(
                MarkupType.Link,
                0,
                24,
                "https://codelabs.developers.google.com/codelabs/on-demand-dynamic-delivery/index.html"
            )
        )
    ),
    Paragraph(
        ParagraphType.Bullet,
        "Patchwork Plaid — A modularization story",
        listOf(
            Markup(
                MarkupType.Link,
                0,
                40,
                "https://medium.com/androiddevelopers/a-patchwork-plaid-monolith-to-modularized-app-60235d9f212e"
            )
        )
    )
)

val post1 = Post(
    id = "dc523f0ed25c",
    title = "A Little Thing about Android Module Paths",
    subtitle = "How to configure your module paths, instead of using Gradle’s default.",
    url = "https://medium.com/androiddevelopers/gradle-path-configuration-dc523f0ed25c",
    publication = publication,
    metadata = Metadata(
        author = pietro,
        date = "August 02",
        readTimeMinutes = 1
    ),
    paragraphs = paragraphsPost1,
    imageId = R.mipmap.ic_launcher,
    imageThumbId = R.mipmap.setting
)

