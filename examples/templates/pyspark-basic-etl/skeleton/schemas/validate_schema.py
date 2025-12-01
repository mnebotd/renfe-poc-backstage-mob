from pyspark.sql import DataFrame
from pyspark.sql.types import StructType

def validate_schema(df: DataFrame, expected_schema: StructType):
    """
    Validates that the DataFrame schema matches the expected schema.

    :param df: Spark DataFrame to validate.
    :param expected_schema: StructType with expected schema.
    :raises Exception: if schema mismatch.
    """

    df_schema = df.schema

    # Column names must match
    df_fields = [(f.name, f.dataType, f.nullable) for f in df_schema.fields]
    expected_fields = [(f.name, f.dataType, f.nullable) for f in expected_schema.fields]

    # Validate columns missing or extra
    df_cols = {f.name for f in df_schema.fields}
    expected_cols = {f.name for f in expected_schema.fields}

    missing = expected_cols - df_cols
    extra = df_cols - expected_cols

    if missing or extra:
        raise Exception(
            f"Schema mismatch:\n"
            f" Missing columns: {missing}\n"
            f" Extra columns: {extra}"
        )

    # Validate type + nullability
    for f_exp, f_df in zip(expected_schema.fields, df_schema.fields):
        if f_exp.dataType != f_df.dataType:
            raise Exception(
                f"Column '{f_exp.name}' has wrong type. "
                f"Expected {f_exp.dataType}, got {f_df.dataType}"
            )

        if f_exp.nullable is False and f_df.nullable is True:
            raise Exception(
                f"Column '{f_exp.name}' expected NON NULLABLE but dataframe allows NULL."
            )

    return True
