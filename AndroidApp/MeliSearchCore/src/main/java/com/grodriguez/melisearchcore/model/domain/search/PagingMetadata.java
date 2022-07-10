package com.grodriguez.melisearchcore.model.domain.search;

public class PagingMetadata {
    private int total = -1;
    private int primaryResults = -1;
    private int offset = -1;
    private int limit = -1;

    public PagingMetadata() {
    }

    // region GET-SET

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPrimaryResults() {
        return primaryResults;
    }

    public void setPrimaryResults(int primaryResults) {
        this.primaryResults = primaryResults;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    // endregion

    // region Public methods

    // Indica si se llego al último registro de la paginación
    public boolean pagingLimitReached() {
        int offsetToApply = applyOffset();
        return offsetToApply == total;
    }

    // Retorna la mayor cantidad de offset que se puede aplicar a una consulta tomando en cuenta
    // el offset ya aplicado
    public int applyOffset() {
        // Se toma como base el offset original más el límite de registros retornados
        int offsetToApply = offset + limit;

        // Si este valor es mayor que el total de registros
        if(offsetToApply > total)
            // El offset a retornar es igual a la cantidad de registros que sobran del total menos el offset
            offsetToApply = total - offset;

        return offsetToApply;
    }

    // Proceso inverso del método applyOffset, en vez de agregar posiciones al offset se resta el límite
    // permitido hasta llegar a cero
    public int removeOffset() {
        int offsetToApply = offset - limit;

        if (offsetToApply < 0)
            offsetToApply = 0;

        return offsetToApply;
    }

    // endregion

}// End Class
