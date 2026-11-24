# MercaSENA · Patrón Builder (Creacional)

**Dominio:** Construcción de pedidos inmutables

## Contexto

Un pedido de MercaSENA tiene datos obligatorios (cliente, dirección y al menos un ítem) y datos opcionales (cupón y notas para el repartidor). Un constructor con siete parámetros es ilegible y propenso a errores, así que se usa **Builder**. Además, el `Pedido` debe ser **inmutable**: una vez construido, nada lo puede cambiar.

Clases principales:

- `Pedido`: objeto inmutable con su clase interna `Pedido.Builder`.
- `ItemPedido`: un *record* con producto, cantidad y precio unitario.

## Reglas de negocio

1. Son obligatorios el **cliente**, la **dirección** y **al menos un ítem**. Si falta alguno, `build()` lanza `IllegalStateException`. Un texto en blanco cuenta como faltante.
2. La cantidad de un ítem debe ser mayor que 0. Si no, se lanza `IllegalArgumentException`.
3. **Inmutabilidad total:** después de `build()`, el pedido no cambia aunque el mismo builder se siga usando para construir otro pedido. La lista que devuelve `getItems()` no se puede modificar (`UnsupportedOperationException`).
4. `total()` es la suma de `precio unitario × cantidad` de todos los ítems.
5. El cupón y las notas son opcionales. Si no se dan, valen `null`.

## Tu misión

Este proyecto implementa el patrón **Builder** dentro de la tienda en línea **MercaSENA**. El código *compila*, pero tiene **errores sembrados**: algunos los detectan las pruebas que ya existen y otros **no**. Tu trabajo es encontrarlos todos, demostrarlos con pruebas y corregirlos con ayuda de la IA, sin perder el control técnico.

> La fuente de verdad son las **reglas de negocio** de este README. La IA no las conoce si no se las das.

## Protocolo de trabajo (obligatorio)

1. **Prepara el repositorio.** Crea un repositorio en GitHub con este proyecto y haz el primer commit sin cambios: `git commit -m "chore: estado inicial"`.
2. **Predice sin IA (20 min).** Lee este README y el código. Escribe tus hipótesis en `BITACORA_IA.md`, sección 1.
3. **Ejecuta las pruebas.** Corre `mvn test` o las pruebas desde IntelliJ / Eclipse. Anota cuáles fallan.
4. **Demuestra cada error con una prueba.** Por cada error que encuentres, incluidos los que las pruebas actuales no detectan, escribe primero una prueba que **falle**. Haz commit: `test: demuestra <error>`.
5. **Consulta al jurado de IAs.** Envía el mismo prompt a ChatGPT, Gemini y DeepSeek. Usa el prompt de 5 piezas: contexto, tarea, restricciones, formato y criterio de verificación. Registra en la bitácora en qué coincidieron y en qué no.
6. **Corrige.** Aplica la corrección que **tú** entiendes y puedes explicar. La prueba debe pasar. Haz commit: `fix: corrige <error>`.
7. **Refactoriza con IA.** Pide a la IA una mejora de legibilidad que no cambie el comportamiento. Todas las pruebas deben seguir en verde. Haz commit: `refactor: ...`.
8. **Revisa la seguridad.** Pregúntate qué podría hacer un usuario malicioso con este código y anota tu respuesta en la bitácora.
9. **Prepara la defensa.** El instructor te pedirá explicar, **sin IA**, cualquier línea que hayas cambiado.

## Cómo ejecutar

```bash
mvn test
```

Requisitos: JDK 17 o superior y Maven 3.9 o superior, o el Maven que trae IntelliJ IDEA o Eclipse.

## Qué NO hacer

- No borres ni debilites pruebas para que pasen.
- No pegues el proyecto completo en una IA sin explicarle las reglas de negocio.
- No aceptes una corrección que no sabes explicar.

## Referencia del patrón

- https://refactoring.guru/es/design-patterns/builder
