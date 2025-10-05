# 📱 RuStore Demo Application

## 📖 Описание проекта
**RuStore Demo Application** — это демонстрационное приложение, реализующее функционал витрины мобильных приложений, аналогичной магазину RuStore.  
Проект состоит из двух частей:
1. **Клиентская часть** — Android-приложение, написанное на **Kotlin (Jetpack Compose)**.  
2. **Серверная часть** — REST API, реализованный на **FastAPI (Python)**, выступающий в роли бэкенда для хранения и выдачи данных о приложениях.

---

## 🧩 Архитектура проекта

### Клиентская часть (Android / Jetpack Compose)
- **MainActivity** — точка входа приложения, инициализирует навигацию.
- **OnBoard** — приветственный экран с логотипом и кнопкой перехода на основную страницу.
- **ProductListScreen** — экран со списком всех доступных приложений.
- **App_Screen** — экран подробной информации о конкретном приложении (описание, скриншоты, кнопка загрузки).
- **Navigation_app** — компонент навигации между экранами.
- **ApiService** — модуль для взаимодействия с REST API (получение списка приложений и конкретных карточек).
- **AppItem**, **ExpandableText**, **AppScreenshots** — UI-компоненты, отвечающие за отображение элементов интерфейса.

Приложение использует:
- **Jetpack Compose** — декларативный UI-фреймворк.
- **Navigation Compose** — управление переходами между экранами.
- **Coil** — библиотеку загрузки изображений.
- **Material 3** — современную библиотеку UI-компонентов.
- **produceState()** — асинхронное получение данных из API с автоматическим обновлением состояния интерфейса.

---

### Серверная часть (FastAPI)
Файл: `main.py`

#### Основные эндпоинты:
| Метод | Путь | Описание |
|--------|------|----------|
| `POST /add_app/` | Добавление нового приложения в базу |
| `GET /apps/` | Получение списка всех приложений |
| `GET /app/{app_id}` | Получение информации о конкретном приложении |

#### Модель данных:
```python
class AppInfo(BaseModel):
    id: int | None
    icon_url: HttpUrl
    title: str
    developer: str
    category: str
    age_rating: str
    description: str
    screenshots: List[HttpUrl]
    apk_url: HttpUrl | None
```

Данные хранятся в оперативной памяти в виде списка `apps_list`, что делает API лёгким и удобным для демонстрации.

---

## ⚙️ Установка и запуск

### 🖥️ 1. Серверная часть (FastAPI)

#### Требования:
- Python 3.10+
- FastAPI
- Uvicorn

#### Установка зависимостей:
```bash
pip install fastapi uvicorn pydantic
```

#### Запуск сервера:
```bash
uvicorn main:app --reload
```

После запуска сервер будет доступен по адресу:
```
http://127.0.0.1:8000
```

#### Примеры запросов:
Добавление приложения:
```bash
POST http://127.0.0.1:8000/add_app/
Content-Type: application/json

{
  "icon_url": "https://example.com/icon.png",
  "title": "Demo App",
  "developer": "Example Corp",
  "category": "Tools",
  "age_rating": "3+",
  "description": "Пример демонстрационного приложения.",
  "screenshots": ["https://example.com/screen1.png"],
  "apk_url": "https://example.com/app.apk"
}
```

---

### 📱 2. Клиентская часть (Android)

#### Требования:
- Android Studio Flamingo или новее
- Gradle 8+
- Kotlin 1.9+

#### Настройка проекта:
1. Откройте проект в Android Studio.
2. Убедитесь, что сервер запущен локально или доступен по сети.
3. В файле `ApiService.kt` укажите URL вашего FastAPI сервера (по умолчанию `http://127.0.0.1:8000`).
4. Соберите и запустите приложение на эмуляторе или физическом устройстве.

---

## 🧱 Структура проекта

```
📂 ProjectRoot
├── 📁 app (Kotlin / Android)
│   ├── MainActivity.kt
│   ├── ProductListScreen.kt
│   ├── App_Screen.kt
│   ├── OnBoard.kt
│   └── ui/
│       ├── ApiService.kt
│       ├── AppItem.kt
│       ├── ExpandableText.kt
│       ├── AppScreenshots.kt
│       └── theme/
│
└── 📁 backend (Python / FastAPI)
    └── main.py
```

---

## 🧪 Пример взаимодействия
1. Пользователь открывает приложение и видит приветственный экран.
2. При нажатии на кнопку **«Войти»** открывается список приложений.
3. При выборе приложения пользователь переходит на экран с подробной информацией.
4. При нажатии **«Скачать»** происходит вызов функции `downloadApk()`, загружающей APK-файл.

---

## 🧰 Технологический стек

**Frontend (Android):**
- Kotlin  
- Jetpack Compose  
- Material 3  
- Coil  
- Navigation Compose  

**Backend:**
- Python 3.10+  
- FastAPI  
- Pydantic  
- Uvicorn  

---
