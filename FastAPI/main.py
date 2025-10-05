from fastapi import FastAPI, HTTPException
from pydantic import BaseModel, HttpUrl
from typing import List

app = FastAPI()

# Модель данных приложения
class AppInfo(BaseModel):
    id: int | None = None                   # ID приложения (устанавливается автоматически)
    icon_url: HttpUrl                       # иконка приложения
    title: str                              # название
    developer: str                          # компания-разработчик
    category: str                           # категория
    age_rating: str                         # возрастной рейтинг
    description: str                         # описание
    screenshots: List[HttpUrl] = []          # скриншоты приложения
    apk_url: HttpUrl | None = None           # ссылка на APK (необязательная)

# "База данных" в памяти
apps_list: List[AppInfo] = []
next_id = 1  # автоинкрементный ID

# Эндпоинт для добавления приложения
@app.post("/add_app/")
def add_app_endpoint(app_data: AppInfo):
    global next_id
    app_data.id = next_id
    next_id += 1
    apps_list.append(app_data)
    return {
        "message": f"Приложение '{app_data.title}' добавлено",
        "id": app_data.id,
        "total_apps": len(apps_list)
    }

# Эндпоинт для получения всех приложений (витрина)
@app.get("/apps/")
def get_apps():
    return {
        "total_apps": len(apps_list),
        "apps": [app.dict() for app in apps_list]
    }

# Эндпоинт для получения конкретного приложения по ID
@app.get("/app/{app_id}")
def get_app(app_id: int):
    for app_item in apps_list:
        if app_item.id == app_id:
            return app_item
    raise HTTPException(status_code=404, detail="Приложение не найдено")

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="127.0.0.1", port=8000, reload=True)
