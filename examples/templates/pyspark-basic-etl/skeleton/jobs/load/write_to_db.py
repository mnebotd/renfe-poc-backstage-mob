from pyspark.sql import DataFrame

def load_data(df: DataFrame):
    """
    Load step: Save output to CSV.

    :param df: DataFrame to write.
    :return: None
    """
    (
        df
        .coalesce(1)
        .write
        .csv('loaded_data', mode='overwrite', header=True)
    )
