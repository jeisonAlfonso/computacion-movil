# Aplicación de Lista de Usuarios - Android Jetpack Compose

## 📱 Descripción del Proyecto

Aplicación Android desarrollada en Jetpack Compose que consume la API de DummyJSON para mostrar una lista de usuarios y su información detallada. Cumple con todos los requisitos del taller especificado.

## ✨ Características Implementadas

### Pantalla Principal (MainScreen)
- ✅ Lista de usuarios con LazyColumn
- ✅ StickyHeader (TopAppBar) mostrando el total de usuarios
- ✅ Cada usuario muestra:
  - Nombre y apellido (firstName + lastName)
  - Nombre de la compañía (company.name)
  - Imagen de perfil (image)
- ✅ ListItem para cada usuario
- ✅ Navegación al hacer clic sobre un usuario

### Pantalla de Detalle (UserDetailScreen)
- ✅ Información completa del usuario seleccionado:
  - Imagen de perfil grande
  - Nombre completo
  - Teléfono (clickeable para abrir el dialer)
  - 6+ campos adicionales:
    1. Empresa
    2. Email
    3. Edad
    4. Género
    5. Universidad
    6. Fecha de nacimiento
    7. Altura
    8. Peso
- ✅ Intent para abrir la aplicación de llamadas al hacer clic en el teléfono
- ✅ Botón de retroceso en el AppBar

## 🏗️ Arquitectura y Tecnologías

### Estructura del Proyecto
```
app/src/main/java/com/example/myapplication/
├── data/
│   ├── model/
│   │   ├── Company.kt          # Data class para empresa
│   │   ├── User.kt             # Data class para usuario
│   │   └── UsersList.kt        # Data class para respuesta API
│   └── remote/
│       └── KtorApiClient.kt    # Cliente HTTP con Ktor
├── navigation/
│   ├── NavigationGraph.kt      # Configuración de navegación
│   └── Screen.kt               # Definición de rutas
├── ui/
│   ├── screens/
│   │   ├── MainScreen.kt       # Pantalla lista de usuarios
│   │   └── UserDetailScreen.kt # Pantalla detalle usuario
│   └── theme/                  # Tema de la aplicación
└── MainActivity.kt             # Actividad principal
```

### Librerías Utilizadas

#### 1. **Ktor Client** (Networking)
- `ktor-client-core`: 3.0.1
- `ktor-client-okhttp`: Motor para Android
- `ktor-client-content-negotiation`: Negociación de contenido
- `ktor-serialization-kotlinx-json`: Serialización JSON
- `ktor-client-logging`: Logging de peticiones

#### 2. **Kotlin Serialization**
- Plugin de serialización aplicado
- `kotlinx-serialization-json`: 1.7.3

#### 3. **Coil** (Carga de imágenes)
- `coil-compose`: 3.0.4 para Jetpack Compose

#### 4. **Navigation Compose**
- `androidx-navigation-compose`: 2.8.6

#### 5. **Jetpack Compose**
- Material3
- Compose BOM: 2024.09.00

## 🎨 Características de Diseño

### Temas Soportados
- ✅ Tema claro (Light Mode)
- ✅ Tema oscuro (Dark Mode)
- Cambia automáticamente según las preferencias del sistema

### Componentes Personalizados
- `UserListItem`: Componente reutilizable para cada elemento de la lista
- `DetailInfoCard`: Tarjeta reutilizable para mostrar información en la pantalla de detalle

## 🔧 Implementación Técnica

### Consulta al API
```kotlin
// En NavigationGraph.kt - Solo se ejecuta UNA vez
LaunchedEffect(Unit) {
    scope.launch {
        try {
            val response = KtorApiClient.getUsers()
            users = response.users
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
```

### Navegación con Parámetros
```kotlin
// Navegación a detalle pasando el ID del usuario
navController.navigate(Screen.Detail.createRoute(userId))

// En la ruta de detalle, se busca el usuario por ID
val user = users.find { it.id == userId }
```

### Intent de Llamada
```kotlin
// Al hacer clic en el teléfono
val intent = Intent(Intent.ACTION_DIAL).apply {
    data = Uri.parse("tel:${user.phone}")
}
context.startActivity(intent)
```

## 📦 Generación del APK

El APK se genera en la ruta:
```
app/build/outputs/apk/debug/app-debug.apk
```

Para generar el APK desde Android Studio:
1. Build → Build Bundle(s) / APK(s) → Build APK(s)
2. O ejecutar: `.\gradlew :app:assembleDebug`

## 🚀 Ejecución del Proyecto

### Requisitos
- Android Studio Hedgehog o superior
- JDK 11 o superior
- Android SDK API 24 o superior
- Conexión a Internet (para consumir la API)

### Pasos
1. Clonar el proyecto
2. Abrir en Android Studio
3. Sincronizar Gradle (Sync Now)
4. Ejecutar en emulador o dispositivo físico

## 📝 Notas de Implementación

### Buenas Prácticas Aplicadas
1. **Separación de responsabilidades**: Modelo, Vista y Lógica separados
2. **Data classes con @Serializable**: Para parseo automático de JSON
3. **Composables reutilizables**: UserListItem y DetailInfoCard
4. **Navegación con type-safe routes**: Usando sealed classes
5. **Manejo de estados**: Con remember y mutableStateOf
6. **Corutinas**: Para llamadas asíncronas al API
7. **Material3**: Siguiendo las últimas guías de diseño de Android

### API Utilizada
- **Endpoint**: https://dummyjson.com/users
- **Respuesta**: Lista de 30 usuarios con información completa
- **Formato**: JSON con nested objects (Company)

## 🎯 Cumplimiento de Requisitos

| Requisito | Estado | Ubicación |
|-----------|--------|-----------|
| Jetpack Compose | ✅ | Todo el proyecto |
| Librería Ktor | ✅ | KtorApiClient.kt |
| Librería Coil | ✅ | AsyncImage en pantallas |
| Tema Light/Dark | ✅ | Theme.kt |
| Lista de usuarios | ✅ | MainScreen.kt |
| ListItem para usuarios | ✅ | UserListItem composable |
| StickyHeader con total | ✅ | TopAppBar en MainScreen |
| Pantalla de detalle | ✅ | UserDetailScreen.kt |
| 6+ campos adicionales | ✅ | 8 campos mostrados |
| Intent de llamadas | ✅ | Click en teléfono |
| Consulta API una vez | ✅ | LaunchedEffect en NavigationGraph |
| Navegación con data class | ✅ | User object compartido |
| Composables personalizados | ✅ | UserListItem, DetailInfoCard |

## 📄 Licencia

Proyecto académico - Pontificia Universidad Javeriana

