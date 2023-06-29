object Versions {
    const val hiltDagger = "2.42"
    const val androidGradle = "7.2.2"
    const val kotlin = "1.7.20"
    const val ktlint = "10.0.0"
    const val navigation = "2.4.1"
    const val retrofit = "2.9.0"
    const val swipeRefresh = "1.2.0-alpha01"
    const val glide = "4.15.1"
}

object Plugins {
    const val ktlint = "org.jlleitschuh.gradle.ktlint"
}

object ClassPath {
    const val hiltDaggerDependency =
        "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltDagger}"
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradle}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val ktlint = "org.jlleitschuh.gradle:ktlint-gradle:${Versions.ktlint}"
}

object Dependencies {

    //Hilt
    const val hiltDaggerAndroid = "com.google.dagger:hilt-android:${Versions.hiltDagger}"
    const val hiltDaggerAndroidCompiler =
        "com.google.dagger:hilt-android-compiler:${Versions.hiltDagger}"

    //Navigator
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationCommon = "androidx.navigation:navigation-common-ktx:${Versions.navigation}"


    //Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    //Swipe to refresh
    const val swipeToRefresh =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefresh}"

    //Glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
}