    package br.com.fiap.to;

    public class CalculoTO {
        private int id;
        private float economia;
        private float retorno;

        public CalculoTO() {
        }

        public CalculoTO(int id, float economia, float retorno) {
            this.id = id;
            this.economia = economia;
            this.retorno = retorno;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public float getEconomia() {
            return economia;
        }

        public void setEconomia(float economia) {
            this.economia = economia;
        }

        public float getRetorno() {
            return retorno;
        }

        public void setRetorno(float retorno) {
            this.retorno = retorno;
        }
    }
