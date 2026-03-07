# 📦 Instrucciones de Empaquetado para Entrega

## 1️⃣ Preparar el Código Fuente

### Opción A: Desde PowerShell (Recomendado)
```powershell
# Navegar a la carpeta del proyecto
cd C:\Users\Jeiso\AndroidStudioProjects\MyApplication

# Crear el archivo ZIP (excluir carpetas innecesarias)
$exclude = @("build", ".gradle", ".idea")
Get-ChildItem -Recurse | 
    Where-Object { $exclude -notcontains $_.Name } |
    Compress-Archive -DestinationPath "TallerAndroid-ListaUsuarios.zip" -Update
```

### Opción B: Manualmente
1. Ir a la carpeta del proyecto
2. Seleccionar TODAS las carpetas EXCEPTO:
   - `build/`
   - `.gradle/`
   - `.idea/`
3. Click derecho → "Enviar a" → "Carpeta comprimida (en zip)"
4. Nombrar: `TallerAndroid-ListaUsuarios.zip`

### Opción C: Desde Android Studio
1. File → Export → Export to Zip File
2. Seleccionar la carpeta del proyecto
3. Guardar como `TallerAndroid-ListaUsuarios.zip`

---

## 2️⃣ Ubicar el APK

El APK ya está generado en:
```
C:\Users\Jeiso\AndroidStudioProjects\MyApplication\app\build\outputs\apk\debug\app-debug.apk
```

### Para re-generarlo (si es necesario):
```powershell
cd C:\Users\Jeiso\AndroidStudioProjects\MyApplication
.\gradlew :app:assembleDebug
```

O desde Android Studio:
1. Build → Build Bundle(s) / APK(s) → Build APK(s)
2. Esperar a que termine
3. Click en "locate" cuando aparezca la notificación

---

## 3️⃣ Grabar el Video

### Herramientas Recomendadas:

#### Windows:
- **OBS Studio** (Gratuito, profesional)
  - Descargar: https://obsproject.com/
  - Tutorial: https://www.youtube.com/watch?v=DTk99mHDX_I

- **ShareX** (Gratuito, sencillo)
  - Descargar: https://getsharex.com/

- **Windows Game Bar** (Ya incluido en Windows 10/11)
  - Presionar: `Win + G`
  - Click en el botón de grabar

#### Mac:
- **QuickTime Player** (Ya incluido)
  - Archivo → Nueva grabación de pantalla

### Configuración de Grabación:
- **Resolución**: 1920x1080 (1080p) mínimo
- **FPS**: 30 o 60
- **Audio**: Asegúrate de que el micrófono esté activado
- **Área**: Pantalla completa o ventana específica

### Proceso de Grabación:

1. **Preparar Android Studio**:
   - Aumentar tamaño de fuente: View → Appearance → Increase Font Size (Ctrl + Shift + NumpadPlus)
   - Cerrar todas las ventanas innecesarias
   - Tener el emulador ya corriendo con la app

2. **Preparar el Script** (usar GUIA_VIDEO.md):
   ```
   ✓ Intro (15s)
   ✓ Demo funcionalidades (1:30)
   ✓ Explicación código (1:00)
   ✓ Cierre (15s)
   Total: 3:00 minutos
   ```

3. **Grabar**:
   - Practica 2-3 veces antes de la versión final
   - Habla claro y pausado
   - Si te equivocas, pausa y reinicia desde ese punto

4. **Editar** (opcional):
   - Cortar errores o pausas largas
   - Agregar intro con tu nombre y código
   - Herramientas: DaVinci Resolve (gratis), CapCut, Windows Video Editor

---

## 4️⃣ Subir a YouTube

### Paso a Paso:

1. **Ir a YouTube Studio**
   - URL: https://studio.youtube.com
   - Iniciar sesión con tu cuenta de Google

2. **Crear / Subir video**
   - Click en el botón "Crear" (esquina superior derecha)
   - Seleccionar "Subir videos"
   - Arrastrar tu archivo de video

3. **Configuración del Video**:

   **Detalles**:
   - **Título**: `Taller Android - Lista de Usuarios con Jetpack Compose`
   - **Descripción**:
     ```
     Taller de Android con Jetpack Compose
     
     Aplicación que consume la API de DummyJSON para mostrar una lista 
     de usuarios y su información detallada.
     
     Tecnologías utilizadas:
     - Jetpack Compose
     - Ktor Client
     - Coil
     - Navigation Compose
     - Kotlin Serialization
     
     Características:
     - Lista de usuarios con LazyColumn
     - Navegación entre pantallas
     - Intent para llamadas telefónicas
     - Soporte para tema claro y oscuro
     
     Pontificia Universidad Javeriana
     ```
   - **Público**: Seleccionar "No, no está hecho para niños"

   **Visibilidad**:
   - ⚠️ **IMPORTANTE**: Seleccionar **"No listado"**
     - Esto permite que solo personas con el enlace puedan verlo
     - No aparecerá en búsquedas ni en tu canal público

4. **Finalizar**:
   - Click en "Siguiente" (3 veces)
   - Click en "Publicar"
   - Esperar a que procese (puede tomar 5-10 minutos)

5. **Copiar Enlace**:
   - Una vez publicado, click en "Ver en YouTube"
   - Copiar la URL completa
   - Ejemplo: `https://www.youtube.com/watch?v=XXXXXXXXXXX`

---

## 5️⃣ Preparar la Entrega

### Crear una carpeta con:

```
TallerAndroid-TuNombre/
├── TallerAndroid-ListaUsuarios.zip    (Código fuente)
├── app-debug.apk                       (APK de la app)
└── README.txt                          (Enlace del video)
```

### Contenido del README.txt:
```
TALLER ANDROID - LISTA DE USUARIOS
Nombre: [Tu Nombre]
Código: [Tu Código]

VIDEO DE DEMOSTRACIÓN:
https://www.youtube.com/watch?v=XXXXXXXXXXX

DESCRIPCIÓN:
Aplicación Android desarrollada en Jetpack Compose que consume
la API de DummyJSON para mostrar una lista de usuarios y su
información detallada.

TECNOLOGÍAS UTILIZADAS:
- Jetpack Compose
- Ktor Client (Networking)
- Coil (Carga de imágenes)
- Navigation Compose
- Kotlin Serialization

APK INCLUIDO:
- app-debug.apk (12.7 MB aprox.)

CÓDIGO FUENTE:
- TallerAndroid-ListaUsuarios.zip
```

### Comprimir todo:
```powershell
Compress-Archive -Path TallerAndroid-TuNombre -DestinationPath TallerAndroid-Entrega-Final.zip
```

---

## ✅ Checklist de Entrega

Antes de enviar, verificar:

### Código Fuente
- [ ] Archivo .zip creado
- [ ] Tamaño razonable (sin carpetas build)
- [ ] Se puede descomprimir correctamente
- [ ] Incluye todos los archivos necesarios

### APK
- [ ] Archivo app-debug.apk incluido
- [ ] Tamaño aprox. 12-15 MB
- [ ] Se puede instalar en un dispositivo

### Video
- [ ] Duración máxima 3 minutos ✓
- [ ] Muestra todas las funcionalidades ✓
- [ ] Explica las partes del código ✓
- [ ] Audio claro y audible ✓
- [ ] Resolución 1080p o superior ✓
- [ ] Subido a YouTube como "No listado" ✓
- [ ] Enlace verificado (se puede abrir) ✓

### Documentación
- [ ] README.txt con enlace del video
- [ ] Datos personales (nombre, código)
- [ ] Todo empaquetado en un solo archivo

---

## 📤 Plataforma de Entrega

Dependiendo de lo que indique el profesor:

### Brightspace / Moodle:
1. Ir a la actividad del taller
2. Subir el archivo .zip final
3. Pegar el enlace del video en el área de comentarios
4. Click en "Enviar"

### Google Drive:
1. Subir archivo a Drive
2. Click derecho → "Obtener enlace"
3. Configurar: "Cualquiera con el enlace"
4. Enviar enlace por el medio indicado

---

## 🆘 Problemas Comunes

### El ZIP es muy grande (>100 MB)
**Solución**: Asegúrate de excluir:
- Carpeta `build/`
- Carpeta `.gradle/`
- Carpeta `.idea/`

### El APK no se instala
**Solución**: 
- Habilitar "Instalar apps de fuentes desconocidas"
- Asegurarte de que es el archivo `app-debug.apk`
- Re-generar con `.\gradlew :app:assembleDebug`

### El video no se sube a YouTube
**Solución**:
- Verificar que sea menor de 15 minutos (tienes 3 min ✓)
- Verificar tu cuenta de YouTube esté verificada
- Intentar con otro navegador
- Comprimir el video si es muy pesado

### El enlace del video no funciona
**Solución**:
- Asegurarte de que esté en "No listado" (no "Privado")
- Copiar la URL completa incluyendo `https://`
- Probar abriendo el enlace en modo incógnito

---

## 📞 Contacto de Emergencia

Si tienes problemas de última hora:
1. Verificar el README_COMPLETO.md
2. Revisar la GUIA_VIDEO.md
3. Consultar con compañeros de clase
4. Escribir al profesor con tiempo

---

## 🎉 ¡Listo para Entregar!

Una vez completados todos los pasos:
- ✅ Código fuente comprimido
- ✅ APK incluido
- ✅ Video grabado y subido
- ✅ Enlace verificado
- ✅ Todo empaquetado

**¡Puedes enviar tu taller con confianza!** 🚀

**Fecha límite**: [Verificar en la plataforma]
**Método de entrega**: [Verificar con el profesor]

---

**NOTA**: Guarda una copia de todo en un lugar seguro (Google Drive, OneDrive, etc.) 
por si necesitas acceder a los archivos después de la entrega.

