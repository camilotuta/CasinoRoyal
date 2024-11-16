# 🎰 Casino Royal

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Java](https://img.shields.io/badge/Java-17-red.svg)](https://www.oracle.com/java/)
[![SQLite](https://img.shields.io/badge/SQLite-3.36-green.svg)](https://www.sqlite.org/)

<div align="center">
  <img src="/api/placeholder/800/400" alt="Casino Royal Logo">
  
  *Tu destino definitivo para el entretenimiento de casino en línea*
</div>

## 📋 Tabla de Contenidos

- [Descripción General](#-descripción-general)
- [Características](#-características)
- [Tecnologías Utilizadas](#-tecnologías-utilizadas)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Instalación](#-instalación)
- [Juegos Disponibles](#-juegos-disponibles)
- [Interfaz de Usuario](#-interfaz-de-usuario)
- [Base de Datos](#-base-de-datos)
- [Seguridad](#-seguridad)
- [Contribución](#-contribución)
- [Licencia](#-licencia)

## 🎲 Descripción General

Casino Royal es una aplicación de casino virtual desarrollada en Java que ofrece una experiencia inmersiva de juegos de azar. Con una interfaz gráfica intuitiva construida con Swing y JFrame, los usuarios pueden disfrutar de varios juegos de casino clásicos, gestionar sus fondos y socializar con otros jugadores.

<div align="center">
  <img src="/api/placeholder/800/400" alt="Casino Interface">
</div>

## ✨ Características

- 🎮 Múltiples juegos de casino
- 💰 Sistema de gestión de fondos
- 💬 Chat en tiempo real
- 📧 Sistema de verificación por correo
- 🔐 Sistema de autenticación seguro
- 💳 Depósitos y retiros
- 🎁 Sistema de cupones promocionales

## 🛠 Tecnologías Utilizadas

- Java 17
- Swing & JFrame para la interfaz gráfica
- SQLite para la base de datos
- JavaMail API para notificaciones por correo
- Sockets para comunicación en tiempo real

## 📁 Estructura del Proyecto

### Clases Principales

```java
├── database/
│   ├── Conexion.java           // Gestión de conexiones a la BD
│   └── OperacionCRUD.java      // Operaciones CRUD
├── communication/
│   ├── EnviarCorreo.java       // Sistema de correos
│   └── ChatCliente.java        // Cliente de chat
├── games/
│   ├── CarreraCarros.java      // Juego de carreras
│   ├── CasillasRuleta.java     // Componentes de ruleta
│   ├── CasillasTragamonedas.java
│   └── PartidaBlackJack.java   // Lógica de BlackJack
└── ui/
    ├── cambiarIU.java          // Modificación de interfaz
    ├── ObtenerIU.java          // Obtención de datos UI
    └── SoundPlay.java          // Sistema de sonido
```

## 🎮 Juegos Disponibles

### 🃏 Póker

- Chat en tiempo real
- Sistema de apuestas
- Múltiples mesas

<div align="center">
  <img src="/api/placeholder/800/400" alt="Poker Game">
</div>

### 🎲 Ruleta

- Apuestas a colores y números
- Historial de resultados
- Animaciones dinámicas

### ♠️ BlackJack

- Modo All-In
- Apuestas personalizables
- Sistema de cartas realista

### 🎰 Tragamonedas

- Múltiples líneas de pago
- Bonus especiales
- Jackpots progresivos

### 🎲 Dados

- Apuestas múltiples
- Sistema de predicción
- Animaciones 3D

### 🏎 Carreras

- Múltiples pistas
- Sistema de favoritos
- Estadísticas en tiempo real

## 🖥 Interfaz de Usuario

### Pantalla de Login

- 🔑 Autenticación segura
- 💡 Sistema de ayuda
- 🔄 Recuperación de contraseña

### Pantalla Principal

- 👥 Indicador de jugadores online
- 💰 Monitor de fondos
- 🎮 Acceso rápido a juegos

### Perfil de Usuario

- 📊 Estadísticas de juego
- 💳 Historial de transacciones
- ✏️ Personalización de biografía

## 💾 Base de Datos

La aplicación utiliza SQLite para almacenar:

- 👤 Información de usuarios
- 💰 Transacciones
- 🎮 Estadísticas de juego
- 🏆 Rankings y logros

## 🔒 Seguridad

- 🔐 Encriptación de contraseñas
- 📧 Verificación por correo electrónico
- 🛡 Protección contra trampas
- 💂‍♂️ Sistema anti-fraude

## 🤝 Contribución

1. Fork del repositorio
2. Crea una nueva rama
3. Realiza tus cambios
4. Envía un pull request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo [LICENSE](LICENSE) para más detalles.

---

<div align="center">
  
  **Desarrollado con ❤️ por el equipo de Casino Royal**
  
  [Reportar Bug](https://github.com/casino-royal/issues) · [Solicitar Función](https://github.com/casino-royal/issues)
</div>
