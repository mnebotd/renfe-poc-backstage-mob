from pyspark.sql import DataFrame
from pyspark.sql.functions import col
from pyspark.sql.types import StructType


def select_safe(df: DataFrame, columns: list) -> DataFrame:
    """
    Selecciona únicamente las columnas que existen en el DataFrame.
    Evita errores cuando faltan columnas en datasets inconsistentes.

    :param df: DataFrame original
    :param columns: lista de columnas a seleccionar
    :return: DataFrame con columnas existentes
    """
    existing_cols = [c for c in columns if c in df.columns]
    return df.select(*existing_cols)


def rename_columns(df: DataFrame, mapping: dict) -> DataFrame:
    """
    Renombra columnas según un diccionario: {"old": "new"}

    :param df: DataFrame original
    :param mapping: dict con old_name → new_name
    :return: DataFrame con columnas renombradas
    """
    for old, new in mapping.items():
        if old in df.columns:
            df = df.withColumnRenamed(old, new)
    return df


def fill_nulls(df: DataFrame, fill_mapping: dict) -> DataFrame:
    """
    Rellena valores nulos según un mapeo por columna.

    :param df: DataFrame original
    :param fill_mapping: {"col_name": value}
    :return: DataFrame sin nulos en las columnas indicadas
    """
    return df.fillna(fill_mapping)


def enforce_schema(df: DataFrame, schema: StructType) -> DataFrame:
    """
    Convierte columnas al tipo especificado en el schema si coincide el nombre.

    :param df: DataFrame original
    :param schema: StructType con schema objetivo
    :return: DataFrame casteado
    """
    for field in schema.fields:
        if field.name in df.columns:
            df = df.withColumn(field.name, col(field.name).cast(field.dataType))
    return df


def repartition_if_needed(df: DataFrame, max_rows_per_partition: int = 5_000_000) -> DataFrame:
    """
    Reparte automáticamente el DataFrame según el volumen de datos,
    evitando particiones demasiado grandes.

    :param df: DataFrame original
    :param max_rows_per_partition: límite de filas por partición
    :return: DataFrame reparticionado si procede
    """
    count = df.count()
    partitions = max(1, count // max_rows_per_partition)

    if partitions > df.rdd.getNumPartitions():
        return df.repartition(partitions)
    return df


def drop_columns(df: DataFrame, columns: list) -> DataFrame:
    """
    Elimina columnas si existen.

    :param df: DataFrame original
    :param columns: lista de columnas a eliminar
    :return: DataFrame sin esas columnas
    """
    for c in columns:
        if c in df.columns:
            df = df.drop(c)
    return df


def assert_not_empty(df: DataFrame, name: str = "DataFrame"):
    """
    Lanza excepción si el DataFrame está vacío.

    :param df: DataFrame a comprobar
    :param name: nombre para el error
    """
    if df.rdd.isEmpty():
        raise Exception(f"❌ {name} está vacío y no debería estarlo.")


def lowercase_columns(df: DataFrame) -> DataFrame:
    """
    Convierte todos los nombres de columna a minúsculas.

    :param df: DataFrame original
    :return: DataFrame con nombres en minúsculas
    """
    for c in df.columns:
        df = df.withColumnRenamed(c, c.lower())
    return df
