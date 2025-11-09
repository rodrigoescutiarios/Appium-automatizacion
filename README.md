# 📱 Framework de Automatización Móvil con Appium

Framework de automatización de pruebas **móviles** desarrollado con **Appium**, **Selenium WebDriver**, **Java**, **Cucumber**, **TestNG** y generación automática de reportes personalizados con **ExtentReports**.

---

## 📋 Requisitos Previos

- **Java JDK** (versión 17 o superior)
- **Maven** (versión 3.8 o superior)
- **Node.js** y **npm** (para instalar Appium)
- **Android SDK** (con `adb` configurado)
- **IntelliJ IDEA** o **VS Code** (recomendado)
- **Dispositivo Android** con depuración USB activada

---

## 🔧 Instalación

### 1. Instalar Java JDK 17+
```bash
# Verificar instalación
java -version
```

### 2. Instalar Maven
```bash
# Verificar instalación
mvn -version
```

### 3. Instalar Node.js y npm
```bash
# Verificar instalación
node --version
npm --version
```

### 4. Instalar Appium
```bash
# Instalar Appium globalmente
npm install -g appium@latest

# Verificar instalación
appium --version
```

### 5. Instalar driver de Android (UiAutomator2)
```bash
appium driver install uiautomator2

# Verificar drivers instalados
appium driver list --installed
```

### 6. Configurar Android SDK

#### Windows:
```powershell
# Configurar variable de entorno ANDROID_HOME
$env:ANDROID_HOME = "C:\Users\TU_USUARIO\AppData\Local\Android\Sdk"

# Configurar permanentemente
[System.Environment]::SetEnvironmentVariable("ANDROID_HOME", "C:\Users\TU_USUARIO\AppData\Local\Android\Sdk", [System.EnvironmentVariableTarget]::User)

# Agregar al PATH
$env:PATH += ";$env:ANDROID_HOME\platform-tools;$env:ANDROID_HOME\tools"
```

#### Linux/Mac:
```bash
# Agregar a ~/.bashrc o ~/.zshrc
export ANDROID_HOME=$HOME/Android/Sdk
export PATH=$PATH:$ANDROID_HOME/platform-tools:$ANDROID_HOME/tools
```

### 7. Verificar ADB
```bash
adb version
```

### 8. Clonar el repositorio
```bash
git clone https://github.com/rodrigoescutiarios/Appium-automatizacion.git
cd mobile-automation-framework
```

### 9. Instalar dependencias del proyecto
```bash
mvn clean install
```

---

## 📱 Configuración del Dispositivo Android

### 1. Habilitar Depuración USB
1. Ve a **Configuración** → **Acerca del teléfono**
2. Toca 7 veces en **Número de compilación**
3. Ve a **Configuración** → **Opciones de desarrollador**
4. Activa **Depuración USB**

### 2. Conectar dispositivo
```bash
# Conectar por USB y verificar
adb devices

# Deberías ver algo como:
# List of devices attached
# ABC123XYZ    device
```

### 3. Obtener información del dispositivo
```bash
# Nombre del dispositivo
adb shell getprop ro.product.model

# ID del dispositivo (UDID)
adb devices

# Versión de Android
adb shell getprop ro.build.version.release
```

---

## 🗂️ Estructura del Proyecto
```
mobile-automation-framework/
├── src/
│   ├── main/java/com/mobile/automation/
│   │   ├── config/
│   │   │   └── DriverManager.java           # Gestión del AppiumDriver
│   │   ├── pages/
│   │   │   ├── BasePage.java                # Clase base para Page Objects
│   │   │   └── ChromePage.java              # Page Object de Chrome
│   │   └── utils/                           # Utilidades generales
│   │
│   └── test/
│       ├── java/com/mobile/automation/
│       │   ├── hooks/
│       │   │   └── Hooks.java               # @Before y @After de Cucumber
│       │   ├── runners/
│       │   │   └── TestRunner.java          # Ejecutor de pruebas
│       │   └── stepdefinitions/
│       │       ├── EjemploSteps.java        # Steps de ejemplo
│       │       └── BusquedaChromeSteps.java # Steps de búsqueda
│       │
│       └── resources/
│           ├── features/
│           │   ├── ejemplo.feature          # Feature de ejemplo
│           │   └── busqueda_chrome.feature  # Feature de búsqueda
│           ├── config/
│           │   └── config.properties        # Configuración de Appium
│           ├── extent.properties            # Config de ExtentReports
│           └── extent-config.xml            # Personalización de reportes
│
├── test-output/
│   ├── ExtentReports/
│   │   └── ExtentReport.html                # Reporte HTML personalizado
│   ├── cucumber-reports/                    # Reportes de Cucumber
│   └── screenshots/                         # Capturas de pantalla
│
├── pom.xml                                  # Dependencias Maven
├── testng.xml                               # Suite de TestNG
└── README.md                                # Documentación
```

---

## 📝 Descripción de Archivos Principales

### `config/config.properties`
Configuración de Appium y dispositivo:
```properties
appium.server.url=http://127.0.0.1:4723
platform.name=Android
device.name=TU_DEVICE_ID
automation.name=UiAutomator2
app.package=com.android.chrome
app.activity=com.google.android.apps.chrome.Main
implicit.wait=10
explicit.wait=20
```

### `config/DriverManager.java`
Gestiona la conexión con Appium:
- Carga configuración desde `config.properties`
- Crea y mantiene una instancia única de AppiumDriver
- Configura capabilities de Android

### `pages/BasePage.java`
Clase base con métodos comunes:
- `click()` - Click con espera explícita
- `sendKeys()` - Escribir texto con validación
- `getText()` - Obtener texto de elementos
- `isDisplayed()` - Verificar visibilidad
- `waitForElement()` - Esperas personalizadas

### `pages/ChromePage.java`
Page Object de Chrome:
- Locators con `@AndroidFindBy`
- Método `searchFor()` para buscar en Google

### `hooks/Hooks.java`
Configuración de pre y post ejecución:
- `@Before` - Inicia el driver antes de cada escenario
- `@After` - Captura screenshots en fallos y cierra el driver

### `runners/TestRunner.java`
Configuración de Cucumber + TestNG:
- Plugins de reportes (HTML, JSON, ExtentReports)
- Ubicación de features y step definitions

---

## ▶️ Ejecución de Pruebas

### Paso 1: Iniciar Appium Server
En una terminal:
```bash
appium
```

Deberías ver:
```
[Appium] Welcome to Appium v3.1.0
[Appium] Appium REST http interface listener started on http://127.0.0.1:4723
```

### Paso 2: Verificar dispositivo conectado
En otra terminal:
```bash
adb devices
```

### Paso 3: Ejecutar todas las pruebas
```bash
mvn clean test
```

### Ejecutar por tags
```bash
# Solo tests @smoke
mvn clean test -Dcucumber.filter.tags="@smoke"

# Tests de búsqueda
mvn clean test -Dcucumber.filter.tags="@search"

# Excluir tags
mvn clean test -Dcucumber.filter.tags="not @wip"
```

### Ejecutar un feature específico
```bash
mvn test -Dcucumber.features="src/test/resources/features/busqueda_chrome.feature"
```

---

## 🔍 Uso de Appium Inspector

### 1. Descargar Appium Inspector
- Descargar desde: https://github.com/appium/appium-inspector/releases

### 2. Configurar conexión
```json
{
  "platformName": "Android",
  "appium:deviceName": "TU_DEVICE_ID",
  "appium:automationName": "UiAutomator2",
  "appium:appPackage": "com.android.chrome",
  "appium:appActivity": "com.google.android.apps.chrome.Main",
  "appium:noReset": true
}
```

**Remote Host:** `127.0.0.1`  
**Remote Port:** `4723`  
**Remote Path:** `/`

### 3. Iniciar sesión
1. Asegúrate de que Appium esté corriendo
2. Click en **Start Session**
3. Inspecciona elementos y copia XPath o resource-id

---

## 📊 Reportes

### Reporte ExtentReports (HTML)
Ubicación:
```
test-output/ExtentReports/ExtentReport.html
```

Abrir:
```bash
# Windows
start test-output\ExtentReports\ExtentReport.html

# Linux/Mac
open test-output/ExtentReports/ExtentReport.html
```

**Características:**
- Dashboard con métricas
- Tests agrupados por features
- Screenshots en fallos
- Tema personalizable (Dark/Light)
- Filtros por estado (Pass/Fail/Skip)

### Reporte Cucumber HTML
```
test-output/cucumber-reports/cucumber-html-report.html
```

### Reporte JSON
```
test-output/cucumber-reports/cucumber.json
```

---

## 🧪 Casos de Prueba Disponibles

| Feature | Scenario | Tag | Descripción |
|---------|----------|-----|-------------|
| ejemplo.feature | Abrir Chrome y verificar que carga | @smoke | Abre Chrome y verifica que la app esté visible |
| busqueda_chrome.feature | Buscar la palabra selenium en Chrome | @search @smoke | Busca "selenium" en Google Chrome |

---

## 🎨 Personalización de Reportes

### Cambiar tema del reporte
Editar `src/test/resources/extent-config.xml`:
```xml
<!-- DARK o STANDARD -->
<theme>DARK</theme>
```

### Agregar información personalizada
Editar `src/test/resources/extent.properties`:
```properties
systeminfo.AppName=Mobile Automation Framework
systeminfo.os=Android
systeminfo.user=TU_NOMBRE
systeminfo.build=1.0.0
```

---

## 📝 Agregar Nuevas Pruebas

### 1. Crear nuevo Feature
`src/test/resources/features/mi_nuevo_test.feature`:
```gherkin
@mobile @mi_app
Feature: Mi nueva funcionalidad
  
  @smoke
  Scenario: Probar nueva funcionalidad
    Given el usuario abre la aplicación
    When el usuario realiza una acción
    Then el resultado debería ser exitoso
```

### 2. Crear Page Object
`src/main/java/com/mobile/automation/pages/MiNuevaPage.java`:
```java
package com.mobile.automation.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class MiNuevaPage extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Mi Botón']")
    private WebElement miBoton;

    public MiNuevaPage(AppiumDriver driver) {
        super(driver);
    }

    public void clickMiBoton() {
        click(miBoton);
    }
}
```

### 3. Crear Step Definitions
`src/test/java/com/mobile/automation/stepdefinitions/MiNuevoSteps.java`:
```java
package com.mobile.automation.stepdefinitions;

import com.mobile.automation.config.DriverManager;
import com.mobile.automation.pages.MiNuevaPage;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class MiNuevoSteps {
    
    private AppiumDriver driver;
    private MiNuevaPage miNuevaPage;

    @Given("el usuario abre la aplicación")
    public void elUsuarioAbreLaAplicacion() {
        driver = DriverManager.getDriver();
        miNuevaPage = new MiNuevaPage(driver);
    }

    @When("el usuario realiza una acción")
    public void elUsuarioRealizaUnaAccion() {
        miNuevaPage.clickMiBoton();
    }

    @Then("el resultado debería ser exitoso")
    public void elResultadoDeberiaSerExitoso() {
        Assert.assertNotNull(driver);
    }
}
```

### 4. Ejecutar
```bash
mvn clean test
```

---

## 🔧 Configurar Otra Aplicación

### 1. Obtener Package y Activity
```bash
# Con la app abierta
adb shell dumpsys window | find "mCurrentFocus"

# O buscar en el APK
aapt dump badging app.apk | find "package"
aapt dump badging app.apk | find "launchable-activity"
```

### 2. Actualizar config.properties
```properties
app.package=com.ejemplo.miapp
app.activity=com.ejemplo.miapp.MainActivity
```

### 3. Obtener locators con Appium Inspector
- Configurar con el nuevo package/activity
- Inspeccionar elementos
- Copiar XPath o resource-id

---

## 🛠️ Comandos ADB Útiles
```bash
# Listar dispositivos
adb devices

# Instalar APK
adb install app.apk

# Desinstalar app
adb uninstall com.ejemplo.miapp

# Ver logs en tiempo real
adb logcat

# Capturar screenshot
adb shell screencap /sdcard/screenshot.png
adb pull /sdcard/screenshot.png

# Obtener estructura de UI
adb shell uiautomator dump
adb pull /sdcard/window_dump.xml

# Limpiar datos de app
adb shell pm clear com.ejemplo.miapp

# Reiniciar ADB server
adb kill-server
adb start-server
```

---

## 💡 Tips y Mejores Prácticas

### Locators
1. **Prioridad**: `resource-id` > `accessibility-id` > `xpath`
2. **XPath**: Usar rutas cortas y robustas
```java
   // ❌ Frágil
   @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]...")
   
   // ✅ Robusto
   @AndroidFindBy(xpath = "//android.widget.Button[@text='Login']")
```

### Esperas
- Usar `waitForElement()` antes de interactuar
- No usar `Thread.sleep()` en producción
- Configurar timeouts adecuados

### Page Object Model
- Una clase por pantalla
- Métodos públicos, locators privados
- Nombres descriptivos

### Features
- Un scenario = un objetivo de prueba
- Usar tags para organizar (@smoke, @regression)
- Escribir en lenguaje de negocio

---

## 🚨 Solución de Problemas Comunes

### Appium no se conecta al dispositivo
```bash
# Verificar dispositivo
adb devices

# Reiniciar ADB
adb kill-server
adb start-server

# Verificar Appium
appium --version
appium driver list --installed
```

### Error: "No route found for /session"
- Estás usando Appium Server GUI (antiguo)
- Solución: Usar `appium` desde terminal

### Element not found
1. Verificar con Appium Inspector
2. Agregar espera explícita
3. Verificar que el elemento sea único

### Versiones incompatibles
```bash
# Limpiar cache de Maven
mvn clean install -U

# Verificar versiones en pom.xml
Appium: 8.6.0
Selenium: 4.15.0
```

### ChromeDriver issues
- Chrome debe estar instalado en el dispositivo
- Usar `appium:chromedriverExecutable` si es necesario

---

## 📦 Dependencias Principales
```xml
<!-- Appium -->
<dependency>
    <groupId>io.appium</groupId>
    <artifactId>java-client</artifactId>
    <version>8.6.0</version>
</dependency>

<!-- Selenium -->
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.15.0</version>
</dependency>

<!-- Cucumber -->
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-java</artifactId>
    <version>7.20.1</version>
</dependency>

<!-- TestNG -->
<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>7.10.2</version>
</dependency>

<!-- ExtentReports -->
<dependency>
    <groupId>tech.grasshopper</groupId>
    <artifactId>extentreports-cucumber7-adapter</artifactId>
    <version>1.14.0</version>
</dependency>
```

---

## 🔄 Integración Continua (CI/CD)

### GitHub Actions
Crear `.github/workflows/tests.yml`:
```yaml
name: Mobile Tests

on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Set up JDK 17
        uses: actions/setup-java@v2
        with:
          java-version: '17'
      - name: Install Node.js
        uses: actions/setup-node@v2
        with:
          node-version: '18'
      - name: Install Appium
        run: npm install -g appium
      - name: Install UiAutomator2
        run: appium driver install uiautomator2
      - name: Run tests
        run: mvn clean test
      - name: Upload reports
        uses: actions/upload-artifact@v2
        with:
          name: test-reports
          path: test-output/
```

---

## 🤝 Contribución

1. Fork el proyecto
2. Crear rama:
```bash
git checkout -b feature/nueva-funcionalidad
```
3. Commit:
```bash
git commit -m 'feat: Agregar nueva funcionalidad'
```
4. Push:
```bash
git push origin feature/nueva-funcionalidad
```
5. Crear **Pull Request**

---

## 📧 Soporte

- **Email**: rodrigoingsis@gmail.com
- **Issues**: [GitHub Issues](https://github.com/TU_USUARIO/mobile-automation-framework/issues)

---

## 📚 Recursos Adicionales

- [Appium Documentation](https://appium.io/docs/en/latest/)
- [Cucumber Documentation](https://cucumber.io/docs/cucumber/)
- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [ExtentReports Documentation](https://www.extentreports.com/)
- [Android ADB Commands](https://developer.android.com/studio/command-line/adb)

---

## 📄 Licencia

Este proyecto es de uso privado. **Todos los derechos reservados.**

---

<p align="center">
  <b>Desarrollado con ❤️ por Jose Rodrigo Escutia Rios</b>
</p>
<p align="center">
  <i>Framework Version: 1.0.0 | Última actualización: 2025</i>
</p>