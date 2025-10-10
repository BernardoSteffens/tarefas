![Logo Universidade Estadual de Ponta Grossa](https://github.com/user-attachments/assets/093a1753-5555-4ef6-92a4-0d6ace536b42)

## Alunos

* Bernardo de Macedo Steffens
* Luis Gustavo Vieira Carneiro
* Vinicius Denega D'Alves

##  Requisições da API
|  **Método** |  **Endpoint**   |  **Descrição**                                                 |  **Códigos HTTP**                                                                       |
| :---------: | :-------------- | :------------------------------------------------------------- | :-------------------------------------------------------------------------------------- |
|    **GET**  | `/`             | Lista todas as tarefas ou filtra por descrição (`?descricao=`).| `200 OK`, `204 NO CONTENT`, `500 INTERNAL SERVER ERROR`                                 |
|    **POST** | `/`             | Cria uma nova tarefa com validação de dados.                   | `201 CREATED`, `400 BAD REQUEST`, `500 INTERNAL SERVER ERROR`                           |
|    **GET**  | `/{id}`         | Retorna uma tarefa específica pelo ID.                         | `200 OK`, `404 NOT FOUND`, `500 INTERNAL SERVER ERROR`                                  |
|    **PUT**  | `/{id}`         | Atualiza os dados de uma tarefa existente.                     | `200 OK`, `400 BAD REQUEST`, `404 NOT FOUND`, `500 INTERNAL SERVER ERROR`               |
|   **DELETE**| `/{id}`         | Remove uma tarefa pelo ID.                                     | `204 NO CONTENT`, `500 INTERNAL SERVER ERROR`                                           |
|    **GET**  | `/pendentes`    | Lista todas as tarefas ainda não concluídas.                   | `200 OK`, `204 NO CONTENT`, `500 INTERNAL SERVER ERROR`                                 |
|   **PATCH** | `/{id}/concluir`| Marca uma tarefa como concluída.                               | `200 OK`, `404 NOT FOUND`, `500 INTERNAL SERVER ERROR`                                  |
