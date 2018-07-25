# SenSocketClient
[![jCenter](https://img.shields.io/badge/NeuroGuide-1.0.1-green.svg) ](https://dl.bintray.com/shenhuanetos/maven/com/shenhua/libs/neuro-guide/1.0.1/)
[![Build Status](https://img.shields.io/travis/rust-lang/rust/master.svg)](https://bintray.com/shenhuanetos/maven/NeuroGuide)
[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

Quick user guide page development.

## Usage

### Maven build settings 

build.gradle
```gradle
dependencies {
  compile 'com.shenhua.libs:neuro-guide:1.0.1'
}
```
or maven
```maven
<dependency>
  <groupId>com.shenhua.libs</groupId>
  <artifactId>neuro-guide</artifactId>
  <version>1.0.1</version>
  <type>pom</type>
</dependency>
```
or lvy
```lvy
<dependency org='com.shenhua.libs' name='neuro-guide' rev='1.0.1'>
  <artifact name='neuro-guide' ext='pom' ></artifact>
</dependency>
```
### Code
Kotlin (TCP-NIO)
```kotlin
val guidePage = GuidePage()
        .addHighLight(tvOval, HighLight.Shape.OVAL)
        .addHighLight(btnRoundRect, HighLight.Shape.ROUND_RECTANGLE)
        .addHighLight(tvRect)
        .addHighLight(tvCircle, 20)
        .setContentView(R.layout.view_guide_1)
        .setNextPageView(R.id.tvNext)
        .setExitView(R.id.tvDismiss)

val guidePage2 = GuidePage()
        .addHighLight(tvOval, HighLight.Shape.OVAL, true)
        .addHighLight(btnRoundRect, HighLight.Shape.ROUND_RECTANGLE, true)
        .addHighLight(tvRect, true)
        .addHighLight(tvCircle, 20, true)
        .setContentView(R.layout.view_guide_2)
        .setNextPageView(R.id.tvNext)

NeuroGuide.with(this, "main")
        .alwaysShow(true)
        .addGuidePage(guidePage)
        .addGuidePage(guidePage2)
        .guideChangedListener(this)
        .pageChangedListener(this)
        .show()
```

### Sample Screenshot

![](https://github.com/shenhuanet/NeuroGuide/blob/master/art/001.png)

![](https://github.com/shenhuanet/NeuroGuide/blob/master/art/002.png)

## About Me
CSDN：http://blog.csdn.net/klxh2009<br>
JianShu：http://www.jianshu.com/u/12a81897d5bc

## License

    Copyright 2018 shenhuanet

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.