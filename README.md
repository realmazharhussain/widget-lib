# WidgetLib

A UI toolkit I made for learning purposes. It's not meant to be used in production.

```kotlin
class MainActivity : WidgetActivity() {
    override fun content() = TopLevel(id = "main").apply {
        child = Column(Size.Fill).apply {
            children += Rectangle(size = 100.dp).apply { foregroundColor = Color.GREEN }
            children += Row(width = Length.Fill, height = 200.dp).apply {
                backgroundColor = Color.WHITE

                children += Rectangle(width = 100.dp, height = 200.dp).apply { foregroundColor = Color.YELLOW }
                children += CenterBox(width = 50.dp, height = Length.Fill).apply {
                    child = Circle(size = 50.dp).apply { foregroundColor = Color.CYAN }
                }
                children += Triangle(size = 200.dp).apply { foregroundColor = Color.MAGENTA }
            }
            children += Rectangle(width = Length.Fill, height = 100.dp).apply { foregroundColor = Color.RED }
            children += CenterBox(Size.Fill).apply {
                child = Text(width = 100.dp, height = Length.Fill).apply {
                    text = "Hello World"
                }
            }
        }
    }
}
```

<img width=33% alt="image" src="https://github.com/user-attachments/assets/52ff7c78-b756-445e-99c0-cf7abf3e1c85" />
