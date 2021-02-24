package me.inver.app

import java.io.File
import java.io.FileNotFoundException
import java.lang.Exception
import java.util.*
import javax.xml.parsers.DocumentBuilderFactory

fun main() {
    printEnvVars()
    printSystemFileSeparator()
    printApplicationProperties()
    printXmlProperties()
}

fun printEnvVars() {
    println("Printing env vars:")
    System.getenv().forEach { println("${it.key}=${it.value}") }
}

fun printSystemFileSeparator() {
    println("System file separator: ${File.separator}")
}

fun printApplicationProperties() {
    println("Printing application.properties files")
    val properties = Properties()
    val propertiesFile = "application.properties"
    val inputStream = object {}.javaClass.classLoader.getResourceAsStream(propertiesFile)
    try {
        if (inputStream != null) {
            properties.load(inputStream)
        } else {
            throw FileNotFoundException("Properties file '$propertiesFile' has been not found.")
        }
        properties.forEach { println("${it.key}: ${it.value}") }
    } catch (e: Exception) {
        println("Exception: $e")
    } finally {
        inputStream!!.close()
    }
}

fun printXmlProperties() {
    println("Printing XML settings")
    val file = File("src/main/resources/settings.xml")
    val documentBuilderFactory = DocumentBuilderFactory.newInstance()
    val documentBuilder = documentBuilderFactory.newDocumentBuilder()
    val document = documentBuilder.parse(file)

    val apiKey = document.getElementsByTagName("api-key").item(0).textContent
    println("ApiKey: $apiKey")
}