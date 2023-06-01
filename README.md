## QR Scanner

A simple QR scanner that uses the Google Vision API.

## Installation

Add it in your root build.gradle at the end of repositories:
```bash
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
Add the dependency:
```bash
dependencies {
  implementation 'com.github.maxwell-xin:password-input:Tag'
}
```

Simple Usage
------------

Example:

```kotlin
PasswordInputComponent(
    label = "Password",
    hint = "Enter Password",
    inputContent = password.collectAsState().value,
    labelColor = colorResource(android.R.color.darker_gray),
    borderColor = colorResource(android.R.color.darker_gray),
    onValueChange = {
        _password.value = InputContent(
            it,
            password.value.error,
            password.value.visible
        )
    },
    onVisibleChange = {
        _password.value = InputContent(
            password.value.value,
            password.value.error,
            it
        )
    }
)    
```
