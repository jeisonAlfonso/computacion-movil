# 🎥 Guía para el Video de Demostración (Máximo 3 minutos)

## 📹 Estructura Sugerida del Video

### 1. Introducción (15 segundos)
- "Hola, les presento mi taller de Android con Jetpack Compose"
- "Esta aplicación consume la API de DummyJSON para mostrar usuarios"

### 2. Demostración de Funcionalidades (1:30 minutos)

#### Pantalla Principal
- Mostrar la lista de usuarios cargada
- Señalar el StickyHeader con el total de usuarios (30)
- Hacer scroll para mostrar varios usuarios
- Explicar que cada ListItem muestra: nombre, apellido, empresa e imagen
- Hacer clic en un usuario para navegar al detalle

#### Pantalla de Detalle
- Mostrar la imagen grande del usuario
- Señalar todos los campos mostrados:
  - Nombre completo
  - Teléfono
  - Empresa
  - Email
  - Edad
  - Género
  - Universidad
  - Fecha de nacimiento
  - Altura
  - Peso
- **IMPORTANTE**: Hacer clic en el teléfono para demostrar el Intent
- Mostrar que se abre la app de llamadas
- Volver atrás con el botón del AppBar

#### Tema Dark Mode
- Cambiar a modo oscuro desde los settings del dispositivo/emulador
- Mostrar que la app se adapta correctamente al tema oscuro
- Navegar entre pantallas en modo oscuro

### 3. Explicación del Código (1 minuto)

#### Navegación y API (NavigationGraph.kt)
```
- Mostrar el archivo NavigationGraph.kt
- Explicar: "Aquí se hace la consulta al API una sola vez con LaunchedEffect"
- Señalar: "La lista de usuarios se comparte entre ambas pantallas"
```

#### Modelos de Datos (data/model/)
```
- Mostrar User.kt y Company.kt
- Explicar: "Los data classes tienen @Serializable para parsear el JSON automáticamente"
```

#### Cliente API (KtorApiClient.kt)
```
- Mostrar el archivo KtorApiClient
- Explicar: "Aquí configuramos Ktor con ContentNegotiation y Logging"
- Señalar la URL de la API: https://dummyjson.com/users
```

#### Pantalla Principal (MainScreen.kt)
```
- Mostrar el composable MainScreen
- Explicar: "Usamos LazyColumn para la lista"
- Señalar: "TopAppBar actúa como StickyHeader con el total de usuarios"
- Mostrar UserListItem: "Composable reutilizable para cada usuario"
```

#### Pantalla de Detalle (UserDetailScreen.kt)
```
- Mostrar el composable UserDetailScreen
- Explicar: "Recibe el objeto User desde la navegación"
- Señalar el Intent de llamada:
  "Al hacer clic en el teléfono, se crea un Intent ACTION_DIAL"
```

#### Configuración Gradle (build.gradle.kts)
```
- Mostrar las dependencias agregadas:
  - Ktor client (core, okhttp, content-negotiation, serialization, logging)
  - Coil para Compose
  - Navigation Compose
  - Kotlinx Serialization
- Mostrar el plugin de serialización aplicado
```

### 4. Cierre (15 segundos)
- "La aplicación cumple con todos los requisitos del taller"
- "Usa Ktor para networking, Coil para imágenes y Navigation Compose"
- "Soporta tema claro y oscuro"
- "Gracias por su atención"

## 🎬 Tips para Grabar

1. **Preparación**:
   - Cierra todas las ventanas innecesarias
   - Aumenta el tamaño de fuente en Android Studio (View → Appearance → Increase Font Size)
   - Ten la app ya compilada y corriendo en el emulador

2. **Herramientas Recomendadas**:
   - OBS Studio (gratuito)
   - Loom (fácil de usar)
   - ShareX (Windows)
   - QuickTime Player (Mac)

3. **Durante la Grabación**:
   - Habla claro y pausado
   - No te extiendas demasiado en cada parte
   - Practica antes de grabar la versión final
   - Cronometra cada sección

4. **Calidad**:
   - Graba a 1080p mínimo
   - Asegúrate de que el audio sea claro
   - Si cometes un error, pausa y continúa (luego puedes editar)

## 📤 Subir a YouTube

1. Ve a YouTube Studio (studio.youtube.com)
2. Click en "Crear" → "Subir videos"
3. Configura el video como:
   - **Título**: "Taller Android Jetpack Compose - Lista de Usuarios"
   - **Descripción**: Breve descripción del proyecto
   - **Visibilidad**: "No listado" (para que solo las personas con el enlace puedan verlo)
4. Copia el enlace y agrégalo a tu entrega

## ✅ Checklist de Demostración

- [ ] App cargando la lista de usuarios
- [ ] StickyHeader visible con total de usuarios
- [ ] Scroll por varios usuarios
- [ ] Navegación a detalle de un usuario
- [ ] Todos los campos visibles en detalle
- [ ] Clic en teléfono abre app de llamadas
- [ ] Botón de retroceso funciona
- [ ] Tema oscuro funciona correctamente
- [ ] Código: NavigationGraph.kt
- [ ] Código: KtorApiClient.kt
- [ ] Código: User.kt con @Serializable
- [ ] Código: MainScreen.kt
- [ ] Código: UserDetailScreen.kt con Intent
- [ ] Código: build.gradle.kts con dependencias

## ⏱️ Distribución de Tiempo

- **0:00 - 0:15**: Introducción
- **0:15 - 0:45**: Demo pantalla principal
- **0:45 - 1:15**: Demo pantalla detalle + Intent
- **1:15 - 1:30**: Demo tema oscuro
- **1:30 - 2:45**: Explicación del código
- **2:45 - 3:00**: Cierre

¡Buena suerte con tu video! 🎥✨

