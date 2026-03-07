# 📝 Snippets de Código para Destacar en el Video

## 🎯 Fragmentos Clave para Mostrar (3 minutos de video)

---

## 1️⃣ NavigationGraph.kt - Consulta Única al API

### Qué decir:
"Aquí está el corazón de la navegación. Usamos LaunchedEffect para consultar la API una sola vez cuando se carga la app."

### Código a señalar:
```kotlin
// LaunchedEffect se ejecuta UNA SOLA VEZ
LaunchedEffect(Unit) {
    scope.launch {
        try {
            // Consulta al API con Ktor
            val response = KtorApiClient.getUsers()
            users = response.users  // Almacenar en memoria
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
```

**Puntos clave**:
- ✓ LaunchedEffect(Unit) - Solo se ejecuta al inicio
- ✓ Corutina (launch) para operación asíncrona
- ✓ users almacenado en memoria compartida

---

## 2️⃣ KtorApiClient.kt - Configuración de Ktor

### Qué decir:
"Este es nuestro cliente HTTP con Ktor. Configuramos ContentNegotiation para parsear JSON automáticamente y Logging para ver las peticiones."

### Código a señalar:
```kotlin
private val client = HttpClient(OkHttp) {
    // Negociación de contenido para JSON
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true  // Importante para APIs
            prettyPrint = true
            isLenient = true
        })
    }
    // Logging para debugging
    install(Logging) {
        level = LogLevel.INFO
    }
}

suspend fun getUsers(): UsersList {
    // GET a la API y parseo automático
    return client.get("$BASE_URL/users").body()
}
```

**Puntos clave**:
- ✓ OkHttp - Motor optimizado para Android
- ✓ ContentNegotiation - Parseo automático de JSON
- ✓ ignoreUnknownKeys - Maneja campos extra de la API
- ✓ Suspend function - Compatible con corutinas

---

## 3️⃣ User.kt - Data Class con @Serializable

### Qué decir:
"Los data classes tienen la anotación @Serializable. Esto permite que Kotlinx Serialization convierta el JSON del API directamente a objetos Kotlin."

### Código a señalar:
```kotlin
@Serializable  // ← Esta anotación hace la magia
data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val image: String,
    val company: Company,  // Objeto anidado
    // Campos opcionales con valores por defecto
    val university: String? = null,
    val height: Double? = null,
    // ... más campos
)

@Serializable
data class Company(
    val name: String,
    val title: String,
    val department: String
)
```

**Puntos clave**:
- ✓ @Serializable - Conversión automática JSON ↔ Objeto
- ✓ Objetos anidados (Company dentro de User)
- ✓ Campos nullable con valores por defecto

---

## 4️⃣ MainScreen.kt - Lista con LazyColumn

### Qué decir:
"La pantalla principal usa LazyColumn para renderizar eficientemente la lista. El TopAppBar actúa como StickyHeader mostrando el total de usuarios."

### Código a señalar:
```kotlin
Scaffold(
    topBar = {
        TopAppBar(
            // StickyHeader con total de usuarios
            title = { Text("Total usuarios: ${users.size}") },
            colors = TopAppBarDefaults.topAppBarColors(...)
        )
    }
) { paddingValues ->
    LazyColumn(...) {
        items(users) { user ->  // Renderiza cada usuario
            UserListItem(
                user = user,
                onClick = { onUserClick(user.id) }  // Navegación
            )
            HorizontalDivider()
        }
    }
}
```

### UserListItem - Composable Reutilizable:
```kotlin
@Composable
fun UserListItem(user: User, onClick: () -> Unit) {
    ListItem(
        headlineContent = { 
            Text("${user.firstName} ${user.lastName}") 
        },
        supportingContent = { 
            Text(user.company.name) 
        },
        leadingContent = {
            AsyncImage(  // Coil para cargar imagen
                model = user.image,
                contentDescription = "Foto de perfil"
            )
        },
        modifier = Modifier.clickable(onClick = onClick)
    )
}
```

**Puntos clave**:
- ✓ LazyColumn - Renderizado eficiente (solo items visibles)
- ✓ TopAppBar - StickyHeader que no desaparece
- ✓ UserListItem - Composable reutilizable
- ✓ AsyncImage - Coil carga imágenes de URLs

---

## 5️⃣ UserDetailScreen.kt - Detalle + Intent

### Qué decir:
"La pantalla de detalle recibe el objeto User desde la navegación. Cuando haces click en el teléfono, se crea un Intent ACTION_DIAL que abre la app de llamadas."

### Código a señalar - Intent de Llamadas:
```kotlin
Card(
    modifier = Modifier
        .fillMaxWidth()
        .clickable {
            // Intent para abrir app de llamadas
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${user.phone}")
            }
            context.startActivity(intent)
        }
) {
    Row(...) {
        Icon(imageVector = Icons.Default.Phone, ...)
        Column {
            Text("Teléfono")
            Text(user.phone, fontWeight = FontWeight.Bold)
        }
    }
}
```

### Composable Reutilizable para Campos:
```kotlin
@Composable
fun DetailInfoCard(label: String, value: String) {
    Card(...) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(label, style = MaterialTheme.typography.labelMedium)
            Text(value, fontWeight = FontWeight.Medium)
        }
    }
}

// Uso:
DetailInfoCard(label = "Empresa", value = user.company.name)
DetailInfoCard(label = "Email", value = user.email)
DetailInfoCard(label = "Edad", value = "${user.age} años")
// ... más campos
```

**Puntos clave**:
- ✓ Intent.ACTION_DIAL - Abre app de llamadas
- ✓ No requiere permisos (solo DIAL, no CALL)
- ✓ DetailInfoCard - Componente reutilizable
- ✓ AsyncImage grande para perfil

---

## 6️⃣ build.gradle.kts - Dependencias

### Qué decir:
"Aquí están todas las dependencias configuradas: Ktor para networking, Coil para imágenes, Navigation Compose y Serialización."

### Código a señalar:
```kotlin
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)  // ← Plugin de serialización
}

dependencies {
    // Kotlinx Serialization
    implementation(libs.kotlinx.serialization.json)
    
    // Ktor Client
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.logging)
    
    // Coil para imágenes
    implementation(libs.coil.compose)
    
    // Navigation Compose
    implementation(libs.androidx.navigation.compose)
}
```

### libs.versions.toml:
```toml
[versions]
ktor = "3.0.1"
coil = "3.0.4"
navigationCompose = "2.8.6"

[libraries]
ktor-client-core = { group = "io.ktor", name = "ktor-client-core", version.ref = "ktor" }
# ... más dependencias
```

**Puntos clave**:
- ✓ Plugin de serialización aplicado
- ✓ Ktor con todas sus dependencias
- ✓ Coil 3.0.4 (última versión para Compose)
- ✓ Navigation Compose para navegación type-safe

---

## 7️⃣ Screen.kt - Navegación Type-Safe

### Qué decir:
"Usamos sealed classes para definir las rutas de navegación de forma type-safe. Esto previene errores al pasar parámetros."

### Código a señalar:
```kotlin
sealed class Screen(val route: String) {
    object Main : Screen("main")
    
    object Detail : Screen("detail/{userId}") {
        // Función helper para crear la ruta con el ID
        fun createRoute(userId: Int) = "detail/$userId"
    }
}

// Uso en NavigationGraph:
navController.navigate(Screen.Detail.createRoute(user.id))

// Extracción del parámetro:
val userId = backStackEntry.arguments?.getInt("userId")
val user = users.find { it.id == userId }
```

**Puntos clave**:
- ✓ Sealed classes - Type-safe
- ✓ Función helper para crear rutas
- ✓ Extracción segura de parámetros
- ✓ Búsqueda en lista en memoria (no nueva consulta API)

---

## 8️⃣ Theme.kt - Soporte Dark Mode

### Qué decir:
"El tema usa Material3 y se adapta automáticamente al modo oscuro del sistema."

### Código a señalar:
```kotlin
@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),  // Detecta tema del sistema
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> darkColorScheme(...)
        else -> lightColorScheme(...)
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
```

**Puntos clave**:
- ✓ isSystemInDarkTheme() - Detecta configuración del sistema
- ✓ Material3 ColorScheme
- ✓ Adaptación automática

---

## 🎬 SECUENCIA SUGERIDA EN EL VIDEO

### Demo (0:15 - 1:45)
1. App abierta → Lista visible
2. Señalar StickyHeader con total
3. Scroll por varios usuarios
4. Click en un usuario
5. Mostrar pantalla de detalle completa
6. **Click en teléfono** → Mostrar que abre app de llamadas
7. Volver atrás
8. Cambiar a Dark Mode → Mostrar adaptación

### Código (1:45 - 2:45)
1. **NavigationGraph.kt** (20s):
   - LaunchedEffect para consulta única
   - Compartir lista entre pantallas

2. **KtorApiClient.kt** (15s):
   - Configuración de Ktor
   - ContentNegotiation + Logging

3. **User.kt** (10s):
   - @Serializable para parseo automático
   - Objetos anidados

4. **MainScreen.kt** (15s):
   - LazyColumn con items
   - UserListItem reutilizable

5. **UserDetailScreen.kt** (15s):
   - Intent.ACTION_DIAL
   - DetailInfoCard reutilizable

6. **build.gradle.kts** (10s):
   - Mostrar dependencias agregadas

---

## 💡 FRASES CLAVE PARA EL VIDEO

1. "La consulta al API se hace **una sola vez** con LaunchedEffect"
2. "Usamos **Ktor** para el networking y **ContentNegotiation** para parsear JSON"
3. "Los data classes tienen **@Serializable** para conversión automática"
4. "**LazyColumn** renderiza eficientemente solo los items visibles"
5. "El **TopAppBar** actúa como StickyHeader permanente"
6. "Al hacer click en el teléfono se crea un **Intent ACTION_DIAL**"
7. "Creamos **composables reutilizables** como UserListItem y DetailInfoCard"
8. "La app soporta **tema claro y oscuro** automáticamente"

---

## ⏱️ TIMING EXACTO (3 minutos)

```
0:00-0:15  Introducción
0:15-0:45  Demo lista + navegación
0:45-1:15  Demo detalle + intent + dark mode
1:15-1:35  Código: NavigationGraph + KtorApiClient
1:35-1:55  Código: MainScreen + UserDetailScreen
1:55-2:15  Código: Models + build.gradle
2:15-2:45  Resumen de arquitectura
2:45-3:00  Cierre
```

---

## 📌 CHECKLIST ANTES DE GRABAR

- [ ] Aumentar tamaño de fuente en Android Studio
- [ ] Cerrar pestañas innecesarias
- [ ] Tener emulador corriendo
- [ ] App ya instalada en el emulador
- [ ] Practicar al menos 2 veces
- [ ] Cronometrar cada sección
- [ ] Verificar que el audio funcione
- [ ] Limpiar pantalla (cerrar notificaciones)

---

¡Con estos snippets tendrás todo listo para un video profesional! 🎥✨

