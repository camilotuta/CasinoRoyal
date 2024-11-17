<!-- cSpell:ignore conexion encriptación inmersiva operacion tragamonedas signup  -->

# 🎰 Casino Royal

[![Java](https://img.shields.io/badge/Java-17-red.svg)](https://www.oracle.com/java/)
[![SQLite](https://img.shields.io/badge/SQLite-3.36-green.svg)](https://www.sqlite.org/)

<div align="center">
  <img src="src/main/resources/assets/img/icon.png" alt="Casino Royal Logo" height=300>
  
  *Tu destino definitivo para el entretenimiento de casino en línea*
</div>

## 📋 Tabla de Contenidos

- [🎰 Casino Royal](#-casino-royal)
  - [📋 Tabla de Contenidos](#-tabla-de-contenidos)
  - [🎲 Descripción General](#-descripción-general)
  - [✨ Características](#-características)
  - [🛠 Tecnologías Utilizadas](#-tecnologías-utilizadas)
  - [📁 Estructura del Proyecto](#-estructura-del-proyecto)
    - [Clases Principales](#clases-principales)
  - [🎮 Juegos Disponibles](#-juegos-disponibles)
    - [🃏 Poker](#-poker)
    - [🎲 Ruleta](#-ruleta)
    - [♠️ BlackJack](#️-blackjack)
    - [🎰 Traga Monedas](#-traga-monedas)
    - [🎲 Dados](#-dados)
    - [🏎 Carrera](#-carrera)
  - [🖥 Interfaz de Usuario](#-interfaz-de-usuario)
    - [Pantalla de Login](#pantalla-de-login)
    - [Pantalla de Registro](#pantalla-de-registro)
    - [Pantalla de Recuperación de Contraseña](#pantalla-de-recuperación-de-contraseña)
    - [Pantalla Principal](#pantalla-principal)
    - [Perfil de Usuario](#perfil-de-usuario)
  - [💾 Base de Datos](#-base-de-datos)
  - [🔒 Seguridad](#-seguridad)

## 🎲 Descripción General

Casino Royal es una aplicación de casino virtual desarrollada en Java que ofrece una experiencia inmersiva de juegos de azar. Con una interfaz gráfica intuitiva construida con Swing y JFrame, los usuarios pueden disfrutar de varios juegos de casino clásicos, gestionar sus fondos y socializar con otros jugadores.

<div align="center">
  <img src="/src/main/resources/assets/img/casinoInterface.png" alt="Casino Interface" height= 400>
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
    ├── CambiarIU.java          // Modificación de interfaz
    ├── ObtenerIU.java          // Obtención de datos UI
    └── SoundPlay.java          // Sistema de sonido
```

## 🎮 Juegos Disponibles

### 🃏 Poker

- Chat en tiempo real
- Sistema de apuestas
- Múltiples mesas

<div align="center">
  <img src="/src/main/resources/assets/img/PokerInterface.png" alt="Casino Interface" height= 400>
</div>

### 🎲 Ruleta

- Apuestas a colores y números
- Historial de resultados
- Animaciones dinámicas

<div align="center">
  <img src="/src/main/resources/assets/img/ruletaInterface.png" alt="Casino Interface" height= 400>
</div>

### ♠️ BlackJack

- Modo All-In
- Apuestas personalizables
- Sistema de cartas realista

<div align="center">
  <img src="/src/main/resources/assets/img/blackJackInterface.png" alt="Casino Interface" height= 400>
</div>

### 🎰 Traga Monedas

- Múltiples líneas de pago
- Bonus especiales
- Jackpots progresivos

<div align="center">
  <img src="/src/main/resources/assets/img/tragaMonedasInterface.png" alt="Casino Interface" height= 400>
</div>

### 🎲 Dados

- Apuestas múltiples
- Sistema de predicción
- Animaciones 3D

<div align="center">
  <img src="/src/main/resources/assets/img/dadosInterface.png" alt="Casino Interface" height= 400>
</div>

### 🏎 Carrera

- Múltiples pistas
- Sistema de favoritos
- Estadísticas en tiempo real

<div align="center">
  <img src="/src/main/resources/assets/img/carreraInterface.png" alt="Casino Interface" height= 400>
</div>

## 🖥 Interfaz de Usuario

### Pantalla de Login

- 🔑 Autenticación segura
- 💡 Sistema de ayuda
- 🔄 Recuperación de contraseña

<div align="center">
  <img src="/src/main/resources/assets/img/loginInterface.png" alt="Casino Interface" height= 400>
</div>

### Pantalla de Registro

- 📝 Formulario completo de registro

- 📅 Selector de fecha de nacimiento

- 📧 Verificación por correo electrónico

- 🔒 Validación de contraseña segura

- 📱 Verificación en dos pasos

<div align="center">

  <img src="/src/main/resources/assets/img/signupInterface.png" alt="Signup Interface" height= 400>

</div>

### Pantalla de Recuperación de Contraseña

- 🔑 Sistema de recuperación seguro

- 📨 Envío de código de verificación

- ⏱️ Tokens de tiempo limitado

- 🔄 Proceso paso a paso

- 🛡️ Validación de identidad

<div align="center">

  <img src="/src/main/resources/assets/img/recoveryInterface.png" alt="Password Recovery Interface" height= 400>

</div>

### Pantalla Principal

- 👥 Indicador de jugadores online
- 💰 Monitor de fondos
- 🎮 Acceso rápido a juegos

<div align="center">
  <img src="/src/main/resources/assets/img/casinoInterface.png" alt="Casino Interface" height= 400>
</div>

### Perfil de Usuario

- 📊 Estadísticas de juego
- 💳 Historial de transacciones
- ✏️ Personalización de biografía

<div align="center">
  <img src="/src/main/resources/assets/img/profileInterface.png" alt="Casino Interface" height= 400>
</div>

<div align="center">
  <img src="/src/main/resources/assets/img/transaccionesInterface.png" alt="Casino Interface" height= 400>

</div>

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

---

<div align="center">
  
  **Desarrollado con ❤️ por el equipo de Casino Royal**
  
  [Reportar Bug](https://github.com/casino-royal/issues) · [Solicitar Función](https://github.com/casino-royal/issues)
</div>
