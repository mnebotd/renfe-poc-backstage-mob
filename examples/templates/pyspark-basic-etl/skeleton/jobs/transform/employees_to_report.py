from pyspark.sql import DataFrame
from pyspark.sql.functions import col, concat_ws, lit

from schemas.employees_report_schema import employees_report_schema
from schemas.validate_schema import validate_schema

def transform_data(df: DataFrame, steps_per_floor: int):
    """
    Transform step: apply business rules.
    """
    df_transformed = (
        df
        .select(
            col("id"),
            concat_ws(" ", col("first_name"), col("second_name")).alias("name"),
            (col("floor") * lit(steps_per_floor)).alias("steps_to_desk")
        )
    )

    # Optional: validate schema
    validate_schema(df_transformed, employees_report_schema)

    return df_transformed
