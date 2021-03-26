import java.util.Properties
import java.io.FileInputStream

val properties = Properties()
val input = FileInputStream(file("../gradle.properties"))
properties.load(input)
properties.stringPropertyNames().forEach { key -> project.extra.set(key, properties.getProperty(key)) }